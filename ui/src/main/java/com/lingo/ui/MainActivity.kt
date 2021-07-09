package com.lingo.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lingo.ui.view.CameraView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<CameraView>(R.id.view)

//        val topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", 0f, 60f)
//        topFlipAnimator.startDelay = 1000
//        topFlipAnimator.duration = 1500
//        topFlipAnimator.start()

//        val bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 0f, 60f)
//        bottomFlipAnimator.startDelay = 1000
//        bottomFlipAnimator.duration = 1500
//        bottomFlipAnimator.start()

        val flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 0f, 60f)
        flipRotationAnimator.startDelay = 1000
        flipRotationAnimator.duration = 1500
        flipRotationAnimator.start()
    }
}