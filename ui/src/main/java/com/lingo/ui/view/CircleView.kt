package com.lingo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lingo.ui.utils.dp

class CircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius = 50.dp
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.color = Color.parseColor("#00796B")
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }
}