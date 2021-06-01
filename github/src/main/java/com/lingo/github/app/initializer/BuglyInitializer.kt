package com.lingo.github.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.lingo.github.app.AppManager
import com.tencent.bugly.crashreport.CrashReport

class BuglyInitializer : BaseInitializer<Unit>() {
    override fun onCreate(context: Context) {
        val config = AppManager.getConfig()
        if (config.buglyAppId != null) {
            val strategy = CrashReport.UserStrategy(context)
            strategy.appChannel = config.channel
            strategy.appVersion = config.appVersionName
            strategy.appPackageName = config.packageName
            strategy.isUploadProcess = config.packageName == config.processName
            CrashReport.initCrashReport(context, config.buglyAppId, config.debug, strategy)
            CrashReport.setIsDevelopmentDevice(context, config.debug)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(AppConfigInitializer::class.java)
    }
}