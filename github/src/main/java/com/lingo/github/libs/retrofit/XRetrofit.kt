package com.lingo.github.libs.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lingo.github.AppContext
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object XRetrofit {
    private val gson: Gson by lazy {
        GsonBuilder()
            .create()
    }

    private val logging: Interceptor by lazy {
        HttpLoggingInterceptor {
            Timber.tag("okhttp").d(it)
        }
            .apply {
                this.setLevel(HttpLoggingInterceptor.Level.BASIC)
            }
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .dispatcher(Dispatcher(AppContext.appExecutors.networkExecutor))
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttp)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(AppContext.appExecutors.uiExecutor)
            .build()
    }

    fun <T> get(service: Class<T>): T {
        return retrofit.create(service)
    }
}