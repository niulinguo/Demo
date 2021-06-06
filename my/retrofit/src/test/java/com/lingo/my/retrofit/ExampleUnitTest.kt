package com.lingo.my.retrofit

import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testRetrofit() {
        val retrofit = Retrofit
            .builder()
            .setBaseUrl("https://api.github.com")
            .setCallFactory(OkHttpClient())
            .build()
        val service = retrofit.create(GitService::class.java)
        service.apiList()
    }
}