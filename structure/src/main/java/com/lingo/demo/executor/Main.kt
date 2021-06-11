package com.lingo.demo.executor

import java.util.concurrent.Executor
import java.util.concurrent.Executors

object Main {

    private val threadLocal = ThreadLocal<Int>()

    @JvmStatic
    fun main(args: Array<String>) {
        val executor: Executor = Executors.newFixedThreadPool(2)

        for (i in 0 until 3) {
            executor.execute(MyRunnable(threadLocal))
        }
    }
}

class MyRunnable(private val threadLocal: ThreadLocal<Int>) : Runnable {
    override fun run() {
        if (threadLocal.get() != null) {
            println("有人改了${Thread.currentThread().name}的ThreadLocal")
        } else {
            threadLocal.set(1)
            println("线程${Thread.currentThread().name}设置成了1")
        }
    }
}