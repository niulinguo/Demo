package com.lingo.kotlin.app.entity

data class Response<D>(
    val data: D,
    val errorCode: Int,
    val errorMsg: String,
)
