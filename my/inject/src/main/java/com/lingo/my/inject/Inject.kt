package com.lingo.my.inject

import android.app.Activity
import android.content.res.Resources
import android.view.View
import com.lingo.my.inject.annotation.OnClick
import com.lingo.my.inject.annotation.OnLongClick
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object Inject {

    fun injectEvent(activity: Activity) {
        activity.javaClass.declaredMethods.forEach { method ->
            if (isEventMethod(method)) {
                method.isAccessible = true

                val annotation = getEventAnnotation(method)

                if (annotation is OnClick) {
                    injectClickEvent(
                        activity,
                        annotation.ids,
                        generateClickListener(activity, method)
                    )
                } else if (annotation is OnLongClick) {
                    injectLongClickEvent(
                        activity,
                        annotation.ids,
                        generateLongClickListener(activity, method)
                    )
                }
            }
        }
    }

    private fun injectClickEvent(
        activity: Activity,
        viewIds: IntArray,
        listener: View.OnClickListener,
    ) {
        for (viewId in viewIds) {
            val view: View = activity.findViewById(viewId)
            view.setOnClickListener(listener)
        }
    }

    private fun injectLongClickEvent(
        activity: Activity,
        viewIds: IntArray,
        listener: View.OnLongClickListener,
    ) {
        for (viewId in viewIds) {
            val view: View = activity.findViewById(viewId)
            view.setOnLongClickListener(listener)
        }
    }

    private fun isEventMethod(method: Method): Boolean {
        val parameterTypes = method.parameterTypes
        if (parameterTypes.size == 1 && parameterTypes[0] is View) {
            return method.annotations.any {
                it is OnClick || it is OnLongClick
            }
        }
        return false
    }

    private fun getEventAnnotation(method: Method): Annotation {
        return method.annotations.find {
            it is OnClick || it is OnLongClick
        } ?: throw Resources.NotFoundException("没有找到注解")
    }

    private fun <T> generateClickListener(obj: T, method: Method): View.OnClickListener {
        checkObj(obj)
        return Proxy.newProxyInstance(
            obj!!::class.java.classLoader,
            arrayOf(View.OnClickListener::class.java),
            ClickInvocationHandler(obj, method),
        ) as View.OnClickListener
    }

    private fun <T> generateLongClickListener(obj: T, method: Method): View.OnLongClickListener {
        checkObj(obj)
        return Proxy.newProxyInstance(
            obj!!::class.java.classLoader,
            arrayOf(View.OnLongClickListener::class.java),
            ClickInvocationHandler(obj, method),
        ) as View.OnLongClickListener
    }

    private fun <T> checkObj(obj: T) {
        if (obj !is Activity && obj !is View) {
            throw IllegalArgumentException("不支持的类型 $obj")
        }
    }

    private class ClickInvocationHandler<T>(private val obj: T, private val method: Method) :
        InvocationHandler {
        override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
            return this.method.invoke(this.obj, args)
        }
    }
}