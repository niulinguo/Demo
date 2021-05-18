package com.lingo.demo.retrofit

import android.util.Log
import com.lingo.demo.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("unused")
object RetrofitDemo {
    fun main() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .validateEagerly(BuildConfig.DEBUG)
            .build()

        val service = retrofit.create(GithubService::class.java)
//        testCall(service)
        testRx(service)
    }

    private fun testRx(service: GithubService) {
        val single = service.listReposRx("octocat")
        single.subscribe()
    }

    fun testCall(service: GithubService) {
        val call = service.listRepos("octocat")
        call.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                val body = response.body()
                if (body != null) {
                    val resp = body[0].toString()
                    Log.i("RetrofitMode", resp)
                }
            }

            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {}
        })
    }
}