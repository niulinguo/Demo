package com.lingo.github.utils

import android.os.Build
import android.os.StrictMode
import android.os.strictmode.*
import timber.log.Timber
import java.util.concurrent.Executor

object StrictModeUtils {

    private const val TAG = "StrictModeUtils"

    private val threadPolicyIgnores = listOf(AwareBitmapCacherIgnore, LeakCanaryIgnore)
    private val vmPolicyIgnores =
        listOf(
            BuglyIgnore,
            WindowInsetsCONSUMEDIgnore,
            ViewComputeFitSystemWindowsIgnore,
            ViewGroupMakeOptionalFitsSystemWindowsIgnore,
            RecyclerViewIgnore,
            OkHttpIgnore,
            RetrofitIgnore,
            GsonIgnore,
        )

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
                    Timber.tag(TAG).e("thread policy")
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
                        Timber.tag(TAG).e("vm policy")
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

object LeakCanaryIgnore : ViolationIgnore {
    override fun ignore(violation: Violation): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                && violation is DiskReadViolation
                && violation.stackTrace.let { stackTrace ->
            stackTrace.any { it.className.startsWith("leakcanary.") }
        }
    }
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

object WindowInsetsCONSUMEDIgnore : ViolationIgnore {
    override fun ignore(violation: Violation): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            violation is NonSdkApiUsedViolation && violation.message == "Landroid/view/WindowInsets;->CONSUMED:Landroid/view/WindowInsets;"
        } else {
            false
        }
    }
}

object ViewComputeFitSystemWindowsIgnore : ViolationIgnore {
    override fun ignore(violation: Violation): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            violation is NonSdkApiUsedViolation && violation.message == "Landroid/view/View;->computeFitSystemWindows(Landroid/graphics/Rect;Landroid/graphics/Rect;)Z"
        } else {
            false
        }
    }
}

object ViewGroupMakeOptionalFitsSystemWindowsIgnore : ViolationIgnore {
    override fun ignore(violation: Violation): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            violation is NonSdkApiUsedViolation && violation.message == "Landroid/view/ViewGroup;->makeOptionalFitsSystemWindows()V"
        } else {
            false
        }
    }
}

object RecyclerViewIgnore : ViolationIgnore {
    private val errorMsgList: List<String> = listOf(
        "Landroid/os/Trace;->TRACE_TAG_APP:J",
        "Landroid/os/Trace;->isTagEnabled(J)Z",
        "Landroid/os/Trace;->asyncTraceBegin(JLjava/lang/String;I)V"
    )

    override fun ignore(violation: Violation): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            violation is NonSdkApiUsedViolation && violation.message in errorMsgList
        } else {
            false
        }
    }
}

object OkHttpIgnore : ViolationIgnore {
    private val errorMsgList: List<String> = listOf(
        "Lcom/android/org/conscrypt/OpenSSLSocketImpl;->setUseSessionTickets(Z)V",
        "Ldalvik/system/CloseGuard;->get()Ldalvik/system/CloseGuard;",
    )

    override fun ignore(violation: Violation): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            violation is NonSdkApiUsedViolation && violation.message in errorMsgList
        } else {
            false
        }
    }
}

object RetrofitIgnore : ViolationIgnore {
    private val errorMsgList: List<String> = listOf(
        "Ljava/lang/invoke/MethodHandles\$Lookup;-><init>(Ljava/lang/Class;I)V",
    )

    override fun ignore(violation: Violation): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            violation is NonSdkApiUsedViolation && violation.message in errorMsgList
        } else {
            false
        }
    }
}

object GsonIgnore : ViolationIgnore {
    private val errorMsgList: List<String> = listOf(
        "Lsun/misc/Unsafe;->theUnsafe:Lsun/misc/Unsafe;",
    )

    override fun ignore(violation: Violation): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            violation is NonSdkApiUsedViolation && violation.message in errorMsgList
        } else {
            false
        }
    }
}