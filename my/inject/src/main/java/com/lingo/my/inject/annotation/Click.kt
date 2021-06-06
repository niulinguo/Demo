package com.lingo.my.inject.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Click(val ids: IntArray)
