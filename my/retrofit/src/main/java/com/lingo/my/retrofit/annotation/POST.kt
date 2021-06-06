package com.lingo.my.retrofit.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class POST(val value: String = "")