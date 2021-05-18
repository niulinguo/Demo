package com.lingo.demo.proxy

import android.util.Log
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object ProxyDemo {

    fun main() {
        val elements = LogPrinter::class.java
        val logPrinter = Proxy.newProxyInstance(
            elements.classLoader, arrayOf(elements),
            fun(proxy: Any?, method: Method, args: Array<Any>) {
                Log.i("ProxyDemo", proxy.toString())
                Log.i("ProxyDemo", method.toString())
                Log.i("ProxyDemo", args.toString())
            }
        )
        if (logPrinter is LogPrinter) {
            logPrinter.print("haha")
        }
    }
}