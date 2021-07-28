package com.lingo.github

import android.R.id
import android.app.Application
import android.content.Context
import com.getkeepsafe.relinker.ReLinker
import com.lingo.github.performance.TimeRecorder
import com.lingo.github.performance.trace.TraceUtils
import com.tencent.mmkv.*
import timber.log.Timber


class MyApp : Application(), MMKVHandler, MMKVContentChangeNotification {
    companion object {
        private const val MMKV_TAG = "mmkv"
        private val mmkvLog = Timber.tag(MMKV_TAG)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        TimeRecorder.startRecord()
        TraceUtils.appLaunchTrace()
    }

    override fun onCreate() {
        super.onCreate()
        TraceUtils.endTrace()

        val dir = filesDir.absolutePath + "/mmkv"
        val rootDir = MMKV.initialize(
            this, dir,
            { libName -> ReLinker.loadLibrary(this@MyApp, libName) }, MMKVLogLevel.LevelInfo
        )
        mmkvLog.i("mmkv root: $rootDir")

        MMKV.registerHandler(this)
        MMKV.registerContentChangeNotify(this)
    }

    override fun onTerminate() {
        MMKV.onExit()
        super.onTerminate()
    }

    override fun onMMKVCRCCheckFail(mmapID: String): MMKVRecoverStrategic {
        return MMKVRecoverStrategic.OnErrorRecover
    }

    override fun onMMKVFileLengthError(mmapID: String?): MMKVRecoverStrategic {
        return MMKVRecoverStrategic.OnErrorRecover
    }

    override fun wantLogRedirecting(): Boolean {
        return true
    }

    override fun mmkvLog(
        level: MMKVLogLevel,
        file: String,
        line: Int,
        func: String,
        message: String
    ) {
        val log =
            "<" + file + ":" + line.toString() + "::" + func + "> " + id.message
        when (level) {
            MMKVLogLevel.LevelDebug -> Timber.d(log)
            MMKVLogLevel.LevelNone, MMKVLogLevel.LevelInfo -> mmkvLog.i(log)
            MMKVLogLevel.LevelWarning -> mmkvLog.w(log)
            MMKVLogLevel.LevelError -> mmkvLog.e(log)
        }
    }

    override fun onContentChangedByOuterProcess(mmapID: String?) {
        mmkvLog.i("other process has changed content of : %s", mmapID)
    }
}