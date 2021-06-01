package com.lingo.github.app.initializer

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.lingo.github.app.AppManager
import timber.log.Timber

abstract class BaseInitializer<T> : Initializer<T> {

    companion object {
        private const val TAG = "Initializer"

        @SuppressLint("LogNotTimber")
        private fun printLog(msg: String) {
            if (!AppManager.getConfig().printLog) {
                return
            }
            if (Timber.treeCount() == 0) {
                // Timber还没有初始化完成
                Log.i(TAG, msg)
            } else {
                Timber.tag(TAG).i(msg)
            }
        }
    }

    override fun create(context: Context): T {
        val startTime: Long = System.currentTimeMillis()
        try {
            return onCreate(context)
        } finally {
            val endTime: Long = System.currentTimeMillis()
            printLog("${this::class.java.simpleName} cost ${endTime - startTime}")
        }
    }

    abstract fun onCreate(context: Context): T
}