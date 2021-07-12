package com.lingo.ui.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import com.lingo.ui.utils.dp

class MeshDrawable : Drawable() {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val interval = 50.dp

    override fun draw(canvas: Canvas) {
        var x = bounds.left.toFloat()
        while (x <= bounds.right) {
            canvas.drawLine(
                x,
                bounds.top.toFloat(),
                x,
                bounds.bottom.toFloat(),
                paint,
            )
            x += interval
        }
        var y = bounds.top.toFloat()
        while (y <= bounds.bottom) {
            canvas.drawLine(
                bounds.left.toFloat(),
                y,
                bounds.right.toFloat(),
                y,
                paint,
            )
            y += interval
        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getAlpha(): Int = paint.alpha

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getColorFilter(): ColorFilter? = paint.colorFilter

    override fun getOpacity(): Int = when (paint.alpha) {
        0 -> PixelFormat.TRANSPARENT
        0xff -> PixelFormat.OPAQUE
        else -> PixelFormat.TRANSLUCENT
    }
}