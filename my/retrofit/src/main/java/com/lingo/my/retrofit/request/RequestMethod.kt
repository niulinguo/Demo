package com.lingo.my.retrofit.request

abstract class RequestMethod(
    val method: String,
    val hasBody: Boolean,
    val relativeUrl: String,
    val paramsHandler: List<ParameterHandler>,
)