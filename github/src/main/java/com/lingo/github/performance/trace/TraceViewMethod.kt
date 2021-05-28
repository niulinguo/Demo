package com.lingo.github.performance.trace

import android.os.Debug
import com.lingo.github.app.AppManager
import java.io.File

class TraceViewMethod : TraceMethod {
    override fun startTrace(tag: String) {
        Debug.startMethodTracing(File(AppManager.context().filesDir, "App_$tag.trace").absolutePath)
    }

    override fun endTrace() {
        Debug.stopMethodTracing()
    }
}