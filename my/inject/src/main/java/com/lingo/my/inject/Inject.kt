package com.lingo.my.inject

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import com.lingo.my.inject.annotation.InjectView
import com.lingo.my.inject.annotation.OnClick
import com.lingo.my.inject.annotation.OnLongClick
import java.lang.reflect.Field
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object Inject {
    fun injectView(activity: Activity) {
        activity.javaClass.declaredFields.forEach { field ->
            if (isInjectView(field)) {
                field.isAccessible = true

                val annotation = getInjectViewAnnotation(field)
                injectView(activity, field, annotation.value)
            }
        }
    }

    fun injectEvent(activity: Activity) {
        activity.javaClass.declaredMethods.forEach { method ->
            if (isClickEvent(method)) {
                method.isAccessible = true

                val annotation = getClickEventAnnotation(method)
                injectClickEvent(
                    activity,
                    annotation.value,
                    generateClickListener(activity, method)
                )
            } else if (isLongClickEvent(method)) {
                method.isAccessible = true

                val annotation = getLongClickEvent(method)
                injectLongClickEvent(
                    activity,
                    annotation.value,
                    generateLongClickListener(activity, method)
                )
            }
        }
    }

    private fun injectView(activity: Activity, field: Field, @IdRes viewId: Int) {
        val view: View = activity.findViewById(viewId)
        field.set(activity, view)
    }

    private fun injectClickEvent(
        activity: Activity,
        @IdRes viewIds: IntArray,
        listener: View.OnClickListener,
    ) {
        for (viewId in viewIds) {
            val view: View = activity.findViewById(viewId)
            view.setOnClickListener(listener)
        }
    }

    private fun injectLongClickEvent(
        activity: Activity,
        @IdRes viewIds: IntArray,
        listener: View.OnLongClickListener,
    ) {
        for (viewId in viewIds) {
            val view: View = activity.findViewById(viewId)
            view.setOnLongClickListener(listener)
        }
    }

    private fun isInjectView(field: Field): Boolean {
        return field.isAnnotationPresent(InjectView::class.java)
    }

    private fun getInjectViewAnnotation(field: Field): InjectView {
        return field.getAnnotation(InjectView::class.java)
    }

    private fun isClickEvent(method: Method): Boolean {
        return method.isAnnotationPresent(OnClick::class.java)
    }

    private fun getClickEventAnnotation(method: Method): OnClick {
        return method.getAnnotation(OnClick::class.java)
    }

    private fun isLongClickEvent(method: Method): Boolean {
        return method.isAnnotationPresent(OnLongClick::class.java)
    }

    private fun getLongClickEvent(method: Method): OnLongClick {
        return method.getAnnotation(OnLongClick::class.java)
    }

    private fun generateClickListener(activity: Activity, method: Method): View.OnClickListener {
        return Proxy.newProxyInstance(
            activity::class.java.classLoader,
            arrayOf(View.OnClickListener::class.java),
            MyInvocationHandler(activity, method),
        ) as View.OnClickListener
    }

    private fun generateLongClickListener(
        activity: Activity,
        method: Method
    ): View.OnLongClickListener {
        return Proxy.newProxyInstance(
            activity::class.java.classLoader,
            arrayOf(View.OnLongClickListener::class.java),
            MyInvocationHandler(activity, method),
        ) as View.OnLongClickListener
    }

    private class MyInvocationHandler<T>(private val obj: T, private val method: Method) :
        InvocationHandler {
        override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
            return this.method.invoke(this.obj, args)
        }
    }
}