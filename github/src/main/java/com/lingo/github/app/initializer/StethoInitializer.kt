package com.lingo.github.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.facebook.stetho.Stetho
import com.lingo.github.app.AppManager

class StethoInitializer : BaseInitializer<Unit>() {
    override fun onCreate(context: Context) {
        if (AppManager.getConfig().stetho) {
            Stetho.initializeWithDefaults(context)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(AppConfigInitializer::class.java)
    }
}