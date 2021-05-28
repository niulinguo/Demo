package com.lingo.github

import android.app.Application
import android.content.Context
import com.lingo.github.performance.TimeRecorder
import com.lingo.github.performance.trace.TraceUtils

class MyApp : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        TimeRecorder.startRecord()
        TraceUtils.appLaunchTrace()
    }

    override fun onCreate() {
        super.onCreate()
        TraceUtils.endTrace()
    }
}