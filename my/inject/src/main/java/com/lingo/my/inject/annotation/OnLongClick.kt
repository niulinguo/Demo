package com.lingo.my.inject.annotation

import androidx.annotation.IdRes

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnLongClick(@IdRes val value: IntArray)
