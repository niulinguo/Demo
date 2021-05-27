package com.lingo.github

import android.os.Bundle
import com.lingo.github.base.BaseActivity
import com.lingo.github.utils.TimeRecorder

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TimeRecorder.endRecord()
    }
}