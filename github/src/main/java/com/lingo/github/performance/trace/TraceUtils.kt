package com.lingo.github.performance.trace

object TraceUtils : TraceMethod {
    private val proxy: TraceMethod = SysTraceMethod()

    fun appLaunchTrace() {
        startTrace("Launch")
    }

    override fun startTrace(tag: String) {
        proxy.startTrace(tag)
    }

    override fun endTrace() {
        proxy.endTrace()
    }
}