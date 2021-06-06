package com.lingo.my.retrofit.request

class PostRequestMethod(relativeUrl: String, paramsHandler: List<ParameterHandler>) : RequestMethod(
    "POST", true, relativeUrl, paramsHandler
)