package com.lingo.github.performance.trace

interface TraceMethod {
    fun startTrace(tag: String)

    fun endTrace()
}