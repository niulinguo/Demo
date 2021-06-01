package com.lingo.github.app.initializer

import android.app.Application
import android.content.Context
import android.os.Process
import androidx.startup.Initializer
import com.lingo.github.BuildConfig
import com.lingo.github.app.AppConfigData
import com.lingo.github.app.AppManager
import java.io.BufferedReader
import java.io.FileReader

class AppConfigInitializer : BaseInitializer<AppConfigData>() {
    override fun onCreate(context: Context): AppConfigData {
        val config = AppConfigData.builder()
            .setContext(context.applicationContext as Application)
            .setPackageName(context.packageName)
            .setProcessName(getProcessName(Process.myPid()) ?: context.packageName)
            .setAppVersionName(BuildConfig.VERSION_NAME)
            .setAppVersionCode(BuildConfig.VERSION_CODE)
            .setDebug(BuildConfig.DEBUG)
            .setPrintLog(BuildConfig.DEBUG)
            .setChannel("default")
            .setBuglyAppId("4154f6b5a4")
            .openStrictMode()
            .openStetho()
            .build()
        AppManager.init(config)
        return config
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private fun getProcessName(pid: Int): String? {
        BufferedReader(FileReader("/proc/$pid/cmdline")).use { reader ->
            val text: String? = reader.readLine()
            return text?.trim { it <= ' ' }
        }
    }
}