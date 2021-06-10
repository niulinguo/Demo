package com.lingo.demo.forkjoin

import java.util.concurrent.ForkJoinPool

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = Utils.makeArray(1_000)
        test1(array)
        test2(array)
    }

    private fun test1(array: IntArray) {
        Timer.runWithTime("test1") {
            Utils.slowSum(array, 0, array.size - 1)
        }
    }

    private fun test2(array: IntArray) {
        Timer.runWithTime("test2") {
            val pool = ForkJoinPool()
            val task = SumTask(array, 0, array.size - 1, array.size / 400)
            pool.execute(task)
            task.join()
        }
    }
}