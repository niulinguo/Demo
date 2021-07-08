package com.lingo.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lingo.ui.utils.dp
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150f.dp
private val LENGTH = RADIUS * 0.8
private const val OPEN_ANGLE = 120
private val DASH_WIDTH = 2f.dp
private val DASH_HEIGHT = 10f.dp
private const val DASH_COUNT = 20

class DashboardView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            this.strokeWidth = 3f.dp
            this.style = Paint.Style.STROKE
        }

    private val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            this.strokeWidth = 3f.dp
            this.style = Paint.Style.STROKE
        }

    private val path = Path()

    private var scale = 10.0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val centerX = width / 2f
        val centerY = height / 2f

        path.reset()
        path.addArc(
            centerX - RADIUS,
            centerY - RADIUS,
            centerX + RADIUS,
            centerY + RADIUS,
            90 + OPEN_ANGLE / 2f,
            360f - OPEN_ANGLE,
        )

        val pathMeasure = PathMeasure(path, false)
        val phase = 1.0f * (pathMeasure.length - DASH_WIDTH) / (DASH_COUNT - 1)
        val dash = Path()
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CCW)
        dashPaint.pathEffect = PathDashPathEffect(dash, phase, 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f

        canvas.drawPath(path, paint)
        canvas.drawPath(path, dashPaint)
        val radians = markToRadians(scale)
        canvas.drawLine(
            centerX, centerY,
            (centerX + LENGTH * cos(radians)).toFloat(),
            (centerY + LENGTH * sin(radians)).toFloat(),
            paint,
        )
        paint.style = Paint.Style.FILL
        canvas.drawCircle(centerX, centerY, 3f.dp, paint)
        paint.style = Paint.Style.STROKE
    }

    private fun markToRadians(mark: Float) =
        Math.toRadians(90.0 + OPEN_ANGLE / 2f + (360f - OPEN_ANGLE) / (DASH_COUNT - 1) * mark)
}