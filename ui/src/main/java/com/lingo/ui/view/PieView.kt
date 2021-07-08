package com.lingo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lingo.ui.utils.dp
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150f.dp
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
private val COLORS =
    arrayOf(
        Color.parseColor("#C2185B"),
        Color.parseColor("#00ACC1"),
        Color.parseColor("#558B2F"),
        Color.parseColor("#5D4037")
    )
private val OFFSET_LENGTH = 20f.dp

class PieView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var selectedIndex = 2

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f

        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {
            val color = COLORS[index]
            paint.color = color

            if (index == selectedIndex) {
                val offsetRadians = Math.toRadians(startAngle + angle / 2.0)
                canvas.save()
                canvas.translate(
                    (OFFSET_LENGTH * cos(offsetRadians)).toFloat(),
                    (OFFSET_LENGTH * sin(offsetRadians)).toFloat(),
                )
            }

            canvas.drawArc(
                centerX - RADIUS,
                centerY - RADIUS,
                centerX + RADIUS,
                centerY + RADIUS,
                startAngle,
                angle,
                true,
                paint,
            )

            if (index == selectedIndex) {
                canvas.restore()
            }

            startAngle += angle
        }
    }

}