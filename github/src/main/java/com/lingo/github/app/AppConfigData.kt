package com.lingo.github.app

import android.app.Application

data class AppConfigData(
    val app: Application,
    val packageName: String,
    val processName: String,
    val appVersionName: String,
    val appVersionCode: Int,
    val debug: Boolean,
    val printLog: Boolean,
    val channel: String,
    val buglyAppId: String?,
    val strictMode: Boolean,
    val stetho: Boolean,
) {
    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private lateinit var context: Application
        private lateinit var packageName: String
        private lateinit var processName: String
        private lateinit var appVersionName: String
        private var appVersionCode: Int = 0
        private var debug: Boolean = false
        private var printLog: Boolean = false
        private lateinit var channel: String
        private var buglyAppId: String? = null
        private var strictMode: Boolean = false
        private var stetho: Boolean = false

        fun setContext(app: Application): Builder {
            this.context = app
            return this
        }

        fun setPackageName(packageName: String): Builder {
            this.packageName = packageName;
            return this
        }

        fun setProcessName(processName: String): Builder {
            this.processName = processName
            return this
        }

        fun setAppVersionName(appVersionName: String): Builder {
            this.appVersionName = appVersionName
            return this
        }

        fun setAppVersionCode(appVersionCode: Int): Builder {
            this.appVersionCode = appVersionCode
            return this
        }

        fun setDebug(debug: Boolean): Builder {
            this.debug = debug
            return this
        }

        fun setPrintLog(printLog: Boolean): Builder {
            this.printLog = printLog;
            return this
        }

        fun setChannel(channel: String): Builder {
            this.channel = channel
            return this
        }

        fun setBuglyAppId(appId: String): Builder {
            this.buglyAppId = appId
            return this
        }

        fun openStrictMode(): Builder {
            this.strictMode = true
            return this
        }

        fun openStetho(): Builder {
            this.stetho = true
            return this
        }

        fun build(): AppConfigData {
            return AppConfigData(
                context,
                packageName,
                processName,
                appVersionName,
                appVersionCode,
                debug,
                printLog,
                channel,
                buglyAppId,
                strictMode,
                stetho,
            )
        }
    }
}