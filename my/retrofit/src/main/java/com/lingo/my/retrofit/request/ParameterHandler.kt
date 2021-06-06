package com.lingo.my.retrofit.request

import com.lingo.my.retrofit.ServiceMethod

interface ParameterHandler {
    fun apply(serviceMethod: ServiceMethod, value: String)
}