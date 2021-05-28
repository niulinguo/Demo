package com.lingo.github.app

import android.content.Context
import androidx.startup.Initializer
import com.lingo.github.utils.StrictModeUtils

class StrictModeInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val config = AppManager.getConfig()
        if (config.strictMode) {
            StrictModeUtils.init(true, AppManager.appExecutors.ioExecutor)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(AppConfigInitializer::class.java)
    }
}