package com.lingo.my.inject.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnLongClick(val ids: IntArray)
