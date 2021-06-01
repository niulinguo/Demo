package com.lingo.github.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.lingo.github.app.AppManager
import com.lingo.github.utils.StrictModeUtils

class StrictModeInitializer : BaseInitializer<Unit>() {
    override fun onCreate(context: Context) {
        val config = AppManager.getConfig()
        if (config.strictMode) {
            StrictModeUtils.init(true, AppManager.appExecutors.ioExecutor)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(AppConfigInitializer::class.java)
    }
}