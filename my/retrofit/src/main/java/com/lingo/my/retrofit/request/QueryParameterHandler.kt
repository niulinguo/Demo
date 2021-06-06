package com.lingo.my.retrofit.request

import com.lingo.my.retrofit.ServiceMethod

class QueryParameterHandler(private val key: String) : ParameterHandler {
    override fun apply(serviceMethod: ServiceMethod, value: String) {
        serviceMethod.addQueryParameter(key, value)
    }
}