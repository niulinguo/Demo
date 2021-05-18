package com.lingo.github.utils

import android.os.Build
import android.os.StrictMode
import android.os.strictmode.DiskReadViolation
import android.os.strictmode.Violation
import java.util.concurrent.Executor

object StrictModeUtils {

    private val threadPolicyIgnores = listOf(AwareBitmapCacherIgnore)

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
                    throw violation
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