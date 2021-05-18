package com.lingo.demo.okhttp

import okhttp3.*
import java.io.IOException

object OkhttpDemo {
    fun main() {
        val url = "https://api.github.com/users/rengwuxian/repos"
        val client: OkHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                println("Response status code: ${response.code}")
            }
        })
    }
}