package com.lingo.demo.forkjoin

import java.util.*

object Utils {

    fun makeArray(len: Int): IntArray {
        val random = Random()
        val array = IntArray(len)
        for (i in 0 until len) {
            array[i] = random.nextInt(len * 3)
        }
        return array
    }

    fun slowSum(array: IntArray, start: Int, end: Int): Int {
        var res = 0
        for (i in start..end) {
            res += array[i]
            Thread.sleep(1)
        }
        return res
    }
}