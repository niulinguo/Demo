package com.lingo.kotlin.app.api

import com.lingo.kotlin.app.entity.LoginResponseData
import com.lingo.kotlin.app.entity.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidAPI {
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<Response<LoginResponseData>>

    @POST("/user/register")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<Response<LoginResponseData>>
}