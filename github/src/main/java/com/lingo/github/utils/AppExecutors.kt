package com.lingo.github.utils

import android.net.TrafficStats
import android.os.Handler
import java.util.concurrent.*

class AppExecutors(handler: Handler) {
    val uiExecutor: HandlerExecutor = HandlerExecutor(handler)

    val ioExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    val networkExecutor: ExecutorService = ThreadPoolExecutor(
        0, Int.MAX_VALUE, 60, TimeUnit.SECONDS,
        SynchronousQueue()
    ) { runnable ->
        object : Thread(runnable, "okHttp Dispatcher") {
            override fun run() {
                TrafficStats.setThreadStatsTag(123)
                super.run()
            }
        }.apply {
            isDaemon = false
        }
    }
}

class HandlerExecutor(private val handler: Handler) : Executor {
    override fun execute(command: Runnable) {
        handler.post(command)
    }
}