package com.lingo.my.retrofit

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.concurrent.ConcurrentHashMap

class Retrofit private constructor(
    val baseUrl: HttpUrl,
    val callFactory: okhttp3.Call.Factory
) {

    private val serviceMethodCache: MutableMap<Method, ServiceMethod> = ConcurrentHashMap()

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf(service),
            object : InvocationHandler {
                override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
                    if (method.declaringClass == Object::class.java) {
                        return method.invoke(this, args)
                    }
                    return loadServiceMethod(method).invoke(args)
                }
            }
        ) as T
    }

    private fun loadServiceMethod(method: Method): ServiceMethod {
        return serviceMethodCache[method] ?: synchronized(serviceMethodCache) {
            serviceMethodCache[method].let {
                if (it == null) {
                    val serviceMethod: ServiceMethod =
                        HttpServiceMethod.createServiceMethod(this, method)
                    serviceMethodCache[method] = serviceMethod
                    return serviceMethod
                } else {
                    it
                }
            }
        }
    }

    class Builder {

        private var baseUrl: HttpUrl? = null
        private var callFactory: okhttp3.Call.Factory? = null

        fun setBaseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl.toHttpUrl()
            return this
        }

        fun setCallFactory(callFactory: okhttp3.Call.Factory): Builder {
            this.callFactory = callFactory
            return this
        }

        fun build(): Retrofit {
            val baseUrl: HttpUrl = this.baseUrl ?: throw IllegalArgumentException("baseUrl 不能为空")
            val callFactory: okhttp3.Call.Factory = this.callFactory ?: OkHttpClient()

            return Retrofit(baseUrl, callFactory)
        }
    }
}