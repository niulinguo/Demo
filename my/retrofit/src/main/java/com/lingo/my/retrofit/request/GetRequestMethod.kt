package com.lingo.my.retrofit.request

class GetRequestMethod(relativeUrl: String, paramsHandler: List<ParameterHandler>) : RequestMethod(
    "GET", false, relativeUrl, paramsHandler
)