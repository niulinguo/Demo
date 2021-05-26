package com.lingo.github.utils

import android.util.Log
import kotlin.properties.Delegates

object TimeRecorder {

    private var time by Delegates.notNull<Long>()

    fun startRecord() {
        time = System.currentTimeMillis()
    }

    fun endRecord() {
        val cost = System.currentTimeMillis() - time
        Log.i("TimeRecorder", "cost $cost")
    }
}