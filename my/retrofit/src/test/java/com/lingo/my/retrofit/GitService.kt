package com.lingo.my.retrofit

import com.lingo.my.retrofit.annotation.GET
import com.lingo.my.retrofit.annotation.Query
import okhttp3.Call

interface GitService {
    @GET("api/sss")
    fun apiList(@Query("name") name: String): Call
}