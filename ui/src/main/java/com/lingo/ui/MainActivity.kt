package com.lingo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val view = findViewById<CameraView>(R.id.view)
//
//        val bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 0f, 60f)
//        bottomFlipAnimator.startDelay = 1000
//        bottomFlipAnimator.duration = 1000
//        bottomFlipAnimator.interpolator = AccelerateInterpolator()
//
//        val flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 0f, 270f)
//        flipRotationAnimator.startDelay = 200
//        flipRotationAnimator.duration = 1000
//        flipRotationAnimator.interpolator = AccelerateDecelerateInterpolator()
//
//        val topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", 0f, -60f)
//        topFlipAnimator.startDelay = 200
//        topFlipAnimator.duration = 1000
//        topFlipAnimator.interpolator = DecelerateInterpolator()
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
//        animatorSet.start()

//        val view = findViewById<MaterialEditText>(R.id.view)
//        view.postDelayed(3000) {
//            view.useFloatingLabel = false
//        }
    }
}