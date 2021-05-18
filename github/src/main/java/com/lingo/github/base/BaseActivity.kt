package com.lingo.github.base

import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.lingo.github.utils.StrictModeUtils

open class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        val oldVmPolicy = StrictModeUtils.allowNonSdkApiUsage()
        super.setContentView(layoutResID)
        StrictMode.setVmPolicy(oldVmPolicy)
    }
}