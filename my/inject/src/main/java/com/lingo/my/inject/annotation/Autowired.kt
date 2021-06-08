package com.lingo.my.inject.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Autowired(val value: String = "")
