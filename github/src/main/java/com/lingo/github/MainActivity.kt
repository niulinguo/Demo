package com.lingo.github

import android.os.Bundle
import com.lingo.github.base.BaseActivity
import com.lingo.github.performance.TimeRecorder

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        TimeRecorder.endRecord("onWindowFocusChanged")
    }
}