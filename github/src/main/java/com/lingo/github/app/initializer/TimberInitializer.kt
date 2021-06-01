package com.lingo.github.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.lingo.github.app.AppManager
import timber.log.Timber

class TimberInitializer : BaseInitializer<Unit>() {

    override fun onCreate(context: Context) {
        if (AppManager.getConfig().printLog) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(AppConfigInitializer::class.java)
    }
}