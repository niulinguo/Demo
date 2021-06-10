package com.lingo.demo.forkjoin

class Timer(private val tag: String) {

    companion object {
        fun runWithTime(tag: String, block: () -> Any) {
            val timer = Timer(tag)
            timer.start()
            val result = block.invoke()
            timer.end("result is $result")
        }
    }

    private var startTime: Long = 0

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun end(msg: String) {
        val time = System.currentTimeMillis()
        val cost = time - startTime
        println("[$tag] cost $cost, $msg")
    }
}