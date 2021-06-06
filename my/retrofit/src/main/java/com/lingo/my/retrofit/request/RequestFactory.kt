package com.lingo.my.retrofit.request

import com.lingo.my.retrofit.annotation.Field
import com.lingo.my.retrofit.annotation.GET
import com.lingo.my.retrofit.annotation.POST
import com.lingo.my.retrofit.annotation.Query
import java.lang.reflect.Method

object RequestFactory {
    fun create(method: Method): RequestMethod {
        val params: MutableList<ParameterHandler> = mutableListOf()

        for (parameterAnnotation in method.parameterAnnotations) {
            for (annotation in parameterAnnotation) {
                when (annotation) {
                    is Field -> {
                        params.add(FieldParameterHandler(annotation.value))
                    }
                    is Query -> {
                        params.add(QueryParameterHandler(annotation.value))
                    }
                    else -> {
                        throw IllegalArgumentException("un support $annotation")
                    }
                }
            }
        }

        for (annotation in method.annotations) {
            if (annotation is POST) {
                return PostRequestMethod(annotation.value, params)
            } else if (annotation is GET) {
                params.any {
                    if (it is FieldParameterHandler) {
                        throw IllegalArgumentException("GET方法不能使用Field注解")
                    }
                    true
                }
                return GetRequestMethod(annotation.value, params)
            }
        }

        throw IllegalArgumentException("需要POST或者GET注解")
    }
}