package com.lingo.demo.forkjoin

import java.util.concurrent.RecursiveTask

class SumTask(
    private val array: IntArray,
    private val start: Int,
    private val end: Int,
    private val maxLen: Int
) : RecursiveTask<Int>() {
    override fun compute(): Int {
        val len = end - start + 1;
        if (len <= maxLen) {
            return Utils.slowSum(array, start, end)
        }
        val mid = (start + end) / 2
        val task1 = SumTask(array, start, mid, maxLen)
        val task2 = SumTask(array, mid + 1, end, maxLen)
        invokeAll(task1, task2)
        return task1.join() + task2.join()
    }
}