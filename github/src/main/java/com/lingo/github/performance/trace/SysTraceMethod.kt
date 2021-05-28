package com.lingo.github.performance.trace

import android.os.Trace

class SysTraceMethod : TraceMethod {
    override fun startTrace(tag: String) {
        Trace.beginSection(tag)
    }

    override fun endTrace() {
        Trace.endSection()
    }
}