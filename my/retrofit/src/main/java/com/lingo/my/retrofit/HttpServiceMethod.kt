package com.lingo.my.retrofit

import com.lingo.my.retrofit.request.RequestFactory
import com.lingo.my.retrofit.request.RequestMethod
import okhttp3.Call
import okhttp3.FormBody
import okhttp3.HttpUrl
import java.lang.reflect.Method

class HttpServiceMethod(retrofit: Retrofit, method: Method) :
    ServiceMethod {

    private val requestMethod: RequestMethod = RequestFactory.create(method)
    private val urlBuilder: HttpUrl.Builder = retrofit.baseUrl.newBuilder(requestMethod.relativeUrl)
        ?: throw IllegalArgumentException("url 不合法")
    private val formBuilder: FormBody.Builder = FormBody.Builder()
    private val callFactory: Call.Factory = retrofit.callFactory

    companion object {
        fun createServiceMethod(retrofit: Retrofit, method: Method): ServiceMethod {
            return HttpServiceMethod(retrofit, method)
        }
    }

    override fun invoke(vararg args: Any): Any {
        for (i in args.indices) {
            val value = args[i]
            if (value !is String) {
                throw IllegalArgumentException("must is String")
            }
            requestMethod.paramsHandler[i].apply(this, value)
        }

        val url = urlBuilder.build()
        val body = if (requestMethod.hasBody) formBuilder.build() else null

        val request = okhttp3.Request.Builder()
            .method(requestMethod.method, body)
            .url(url.toUrl())
            .build()

        return callFactory.newCall(request)
    }

    override fun addQueryParameter(key: String, value: String) {
        urlBuilder.addQueryParameter(key, value)
    }

    override fun addFieldParameter(key: String, value: String) {
        formBuilder.add(key, value)
    }
}