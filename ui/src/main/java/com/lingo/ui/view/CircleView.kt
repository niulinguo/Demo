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
    private var radius = 50.dp.toInt()
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.color = Color.parseColor("#00796B")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = paddingLeft + paddingRight + radius * 2
        val height = paddingTop + paddingBottom + radius * 2
        val finalWidth = resolveSize(width, widthMeasureSpec)
        val finalHeight = resolveSize(height, heightMeasureSpec)
        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(
            width / 2f,
            height / 2f,
            radius.toFloat(),
            paint,
        )
    }
}