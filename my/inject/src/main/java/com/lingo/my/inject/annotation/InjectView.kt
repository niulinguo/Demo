package com.lingo.my.inject.annotation

import androidx.annotation.IdRes

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class InjectView(@IdRes val value: Int)
