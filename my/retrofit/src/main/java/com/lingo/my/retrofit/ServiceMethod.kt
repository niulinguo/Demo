package com.lingo.my.retrofit

interface ServiceMethod {
    fun invoke(vararg args: Any): Any

    fun addQueryParameter(key: String, value: String)

    fun addFieldParameter(key: String, value: String)
}