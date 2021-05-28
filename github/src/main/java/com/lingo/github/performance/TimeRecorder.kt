package com.lingo.github.performance

import timber.log.Timber
import kotlin.properties.Delegates

object TimeRecorder {

    private const val TAG: String = "TimeRecorder"

    private var time by Delegates.notNull<Long>()

    fun startRecord() {
        time = System.currentTimeMillis()
    }

    fun endRecord() {
        endRecord("")
    }

    fun endRecord(msg: String) {
        val cost = System.currentTimeMillis() - time
        Timber.tag(TAG).i("$msg cost $cost")
    }
}