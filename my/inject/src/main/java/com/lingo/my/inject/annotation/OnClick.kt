package com.lingo.my.inject.annotation

import androidx.annotation.IdRes

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnClick(@IdRes val value: IntArray)
