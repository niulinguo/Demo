package com.lingo.my.inject

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.startup.Initializer

class InjectInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val application: Application = context as Application
        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                Inject.inject(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                Inject.uninstall(activity)
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}