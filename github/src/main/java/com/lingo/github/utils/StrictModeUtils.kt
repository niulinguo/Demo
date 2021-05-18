package com.lingo.github.utils

import android.os.Build
import android.os.StrictMode
import android.os.strictmode.*
import java.util.concurrent.Executor

object StrictModeUtils {

    private val threadPolicyIgnores = listOf(AwareBitmapCacherIgnore)
    private val vmPolicyIgnores = listOf(BuglyIgnore)

    fun init(debug: Boolean, executor: Executor) {
        if (debug) {
            initThreadPolicy(executor)
            initVmPolicy(executor)
        }
    }

    private fun initThreadPolicy(executor: Executor) {
        val builder = StrictMode.ThreadPolicy.Builder()
            .detectAll()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            builder.penaltyListener(executor) { violation ->
                if (threadPolicyIgnores.all { !it.ignore(violation) }) {
                    println("thread policy")
                    throw violation
                }
            }
        }

        StrictMode.setThreadPolicy(
            builder.build()
        )

    }

    private fun initVmPolicy(executor: Executor) {
        val builder = StrictMode.VmPolicy.Builder()
            .detectAll()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            builder
                .detectNonSdkApiUsage()
                .penaltyListener(executor) { violation ->
                    if (vmPolicyIgnores.all { !it.ignore(violation) }) {
                        println("vm policy")
                        throw violation
                    }
                }
        }

        StrictMode.setVmPolicy(
            builder.build()
        )
    }

    fun allowNonSdkApiUsage(): StrictMode.VmPolicy {
        val oldVmPolicy = StrictMode.getVmPolicy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val builder = StrictMode.VmPolicy.Builder(oldVmPolicy)
                .permitNonSdkApiUsage()
            StrictMode.setVmPolicy(builder.build())
        }
        return oldVmPolicy
    }
}

interface ViolationIgnore {
    fun ignore(violation: Violation): Boolean
}

object AwareBitmapCacherIgnore : ViolationIgnore {
    override fun ignore(violation: Violation): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                && violation is DiskReadViolation
                && violation.stackTrace.let { stackTrace ->
            stackTrace.any { it.className == "android.graphics.AwareBitmapCacher" }
        }
    }
}

object BuglyIgnore : ViolationIgnore {
    override fun ignore(violation: Violation): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && violation is LeakedClosableViolation) {
            return violation.cause?.stackTrace?.let { stackTrace ->
                stackTrace.any { it.className.startsWith("com.tencent.bugly") }
            } ?: false
        }
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                && (violation is UntaggedSocketViolation || violation is NonSdkApiUsedViolation)
                && violation.stackTrace.let { stackTrace ->
            stackTrace.any { it.className.startsWith("com.tencent.bugly") }
        }
    }
}