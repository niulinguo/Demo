package com.lingo.github.app

import android.content.Context
import androidx.startup.Initializer
import com.facebook.stetho.Stetho

class StethoInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (AppManager.getConfig().stetho) {
            Stetho.initializeWithDefaults(context)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(AppConfigInitializer::class.java)
    }
}