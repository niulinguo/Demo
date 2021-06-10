package com.lingo.demo.producerconsumer

import java.util.concurrent.CountDownLatch

class MyRunnable(private val runnable: Runnable, private val countDownLatch: CountDownLatch) :
    Runnable {
    override fun run() {
        countDownLatch.await()
        runnable.run()
    }
}