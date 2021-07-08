package com.lingo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.lingo.ui.R
import com.lingo.ui.utils.dp

private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDTH = 20.dp
private val RADIUS = 150.dp

class SportView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            this.style = Paint.Style.FILL
            this.color = HIGHLIGHT_COLOR
            this.textSize = 100.dp
            this.typeface = ResourcesCompat.getFont(context, R.font.zen_loop)
            this.isFakeBoldText = true
            this.textAlign = Paint.Align.CENTER
        }
    private var showText = "abfp"
    private val textBounds = Rect()
        .apply {
            textPaint.getTextBounds(showText, 0, showText.length, this)
        }
    private val fontMetrics = Paint.FontMetrics()
        .apply {
            textPaint.getFontMetrics(this)
        }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f

        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(centerX, centerY, RADIUS, paint)

        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            centerX - RADIUS,
            centerY - RADIUS,
            centerX + RADIUS,
            centerY + RADIUS,
            -90f,
            225f,
            false,
            paint,
        )

//        val offsetY = (textBounds.bottom - textBounds.top) / 2f
        val offsetY = -(fontMetrics.ascent + fontMetrics.descent) / 2f
        canvas.drawText(showText, centerX, centerY + offsetY, textPaint)

        paint.strokeWidth = 1.dp
        paint.color = Color.CYAN
        canvas.drawLine(centerX - RADIUS, centerY, centerX + RADIUS, centerY, paint)

        textPaint.textAlign = Paint.Align.LEFT
        textPaint.textSize = 150.dp
        textPaint.getTextBounds(showText, 0, showText.length, textBounds)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText(showText, 0f - textBounds.left, 0f - textBounds.top, textPaint)

        textBounds.offset(-textBounds.left, -textBounds.top)
        canvas.drawRect(textBounds, paint)

        textPaint.textSize = 15.dp
        textPaint.getTextBounds(showText, 0, showText.length, textBounds)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText(showText, 0f - textBounds.left, 0f - textBounds.top, textPaint)

        textBounds.offset(-textBounds.left, -textBounds.top)
        canvas.drawRect(textBounds, paint)
    }
}