package com.lingo.github

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import com.lingo.github.utils.AppExecutors
import com.lingo.github.utils.StrictModeUtils

class MyApp : Application() {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val appExecutors: AppExecutors = AppExecutors(handler)

    override fun onCreate() {
        super.onCreate()
        StrictModeUtils.init(BuildConfig.DEBUG, appExecutors.ioExecutor)
        val oldThreadPolicy = StrictMode.allowThreadDiskReads()
        handler.post {
            StrictMode.setThreadPolicy(oldThreadPolicy)
        }
    }
}