package com.lingo.my.inject

import android.app.Activity
import android.view.View
import com.lingo.my.inject.annotation.Click
import java.lang.reflect.Method

object Inject {

    fun inject(activity: Activity) {
        activity.javaClass.declaredMethods.forEach { method ->
            if (isClickMethod(method)) {
                injectClick(activity, method, method.getAnnotation(Click::class.java))
            }
        }
    }

    fun uninstall(activity: Activity) {
        activity.javaClass.declaredMethods.forEach { method ->
            if (isClickMethod(method)) {
                uninstallClick(activity, method.getAnnotation(Click::class.java))
            }
        }
    }

    private fun isClickMethod(method: Method): Boolean {
        val parameterTypes = method.parameterTypes
        if (parameterTypes.size == 1 && parameterTypes[0] is View) {
            return method.annotations.any { it is Click }
        }
        return false
    }

    private fun injectClick(activity: Activity, method: Method, clickAnnotation: Click) {
        clickAnnotation.ids.forEach { idRes ->
            activity.findViewById<View>(idRes).setOnClickListener { view ->
                method.invoke(activity, view)
            }
        }
    }

    private fun uninstallClick(activity: Activity, clickAnnotation: Click) {
        clickAnnotation.ids.forEach { idRes ->
            activity.findViewById<View>(idRes).setOnClickListener(null)
        }
    }
}