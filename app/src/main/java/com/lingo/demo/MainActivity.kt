package com.lingo.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lingo.demo.proxy.ProxyDemo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        RetrofitDemo.main()
//        OkhttpDemo.main()
        ProxyDemo.main()
    }
}