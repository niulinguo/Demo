package com.lingo.github.app

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.lingo.github.utils.AppExecutors

object AppManager {
    private lateinit var config: AppConfigData
    private var hasInit: Boolean = false
    private val handler: Handler = Handler(Looper.getMainLooper())
    val appExecutors: AppExecutors = AppExecutors(handler)

    fun init(config: AppConfigData) {
        if (hasInit) {
            throw RuntimeException("不要重复初始化config")
        }
        this.hasInit = true
        this.config = config
    }

    fun getConfig(): AppConfigData {
        return this.config
    }

    fun mainHandler(): Handler {
        return this.handler
    }

    fun context(): Context {
        return getConfig().app
    }
}