package com.lingo.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lingo.demo.retrofit.RetrofitDemo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitDemo.main()
    }
}