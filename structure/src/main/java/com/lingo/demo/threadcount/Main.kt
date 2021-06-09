package com.lingo.demo.threadcount

import java.lang.management.ManagementFactory

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val threadMXBean = ManagementFactory.getThreadMXBean()
        val threads = threadMXBean.dumpAllThreads(false, false)
        threads.forEach {
            println("[${it.threadId}] ${it.threadName}")
        }
    }
}