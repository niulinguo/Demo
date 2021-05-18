package com.lingo.github.utils

import android.os.Handler
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppExecutors(handler: Handler) {
    val uiExecutor: HandlerExecutor = HandlerExecutor(handler)
    val ioExecutor: ExecutorService = Executors.newSingleThreadExecutor()
    val networkExecutor: ExecutorService = Executors.newFixedThreadPool(3)
}

class HandlerExecutor(private val handler: Handler) : Executor {
    override fun execute(command: Runnable) {
        handler.post(command);
    }
}