package com.lingo.github

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.lingo.github.utils.AppExecutors
import com.lingo.github.utils.StrictModeUtils
import com.tencent.bugly.crashreport.CrashReport
import java.io.BufferedReader
import java.io.FileReader

@Suppress("unused")
class MyApp : Application() {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val appExecutors: AppExecutors = AppExecutors(handler)

    override fun onCreate() {
        super.onCreate()

        val context: Context = this
        val packageName = context.packageName
        val processName = getProcessName(android.os.Process.myPid())
        val appVersion = BuildConfig.VERSION_NAME
        val debug = BuildConfig.DEBUG
        val channel = "default"

        // init bugly
        val strategy = CrashReport.UserStrategy(this)
        strategy.appChannel = channel
        strategy.appVersion = appVersion
        strategy.appPackageName = packageName
        strategy.isUploadProcess = processName == null || processName == packageName
        CrashReport.initCrashReport(this, "4154f6b5a4", debug, strategy)
        CrashReport.setIsDevelopmentDevice(this, debug)

        // init strict mode
        StrictModeUtils.init(debug, appExecutors.ioExecutor)
        val oldThreadPolicy = StrictMode.allowThreadDiskReads()
        handler.post {
            StrictMode.setThreadPolicy(oldThreadPolicy)
        }

        Stetho.initializeWithDefaults(context)
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