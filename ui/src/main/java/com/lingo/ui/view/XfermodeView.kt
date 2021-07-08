package com.lingo.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lingo.ui.utils.dp

class XfermodeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
    private val paddingLeft = 150f.dp
    private val paddingTop = 50f.dp
    private val radius = 50f.dp
    private val bounds = RectF(
        paddingLeft,
        paddingTop,
        paddingLeft + 3 * radius,
        paddingTop + 3 * radius,
    )
    private val circleBitmap = Bitmap.createBitmap(
        (radius * 3).toInt(),
        (radius * 3).toInt(),
        Bitmap.Config.ARGB_8888,
    ).apply {
        val canvas = Canvas(this)
        paint.color = Color.parseColor("#D81B60")
        canvas.drawOval(radius, 0f, 3 * radius, 2 * radius, paint)
    }

    private val squareBitmap = Bitmap.createBitmap(
        (radius * 3).toInt(),
        (radius * 3).toInt(),
        Bitmap.Config.ARGB_8888,
    ).apply {
        val canvas = Canvas(this)
        paint.color = Color.parseColor("#2196F3")
        canvas.drawRect(0f, radius, 2 * radius, 3 * radius, paint)
    }

    override fun onDraw(canvas: Canvas) {
        val saveLayerCount = canvas.saveLayer(bounds, null)
        canvas.drawBitmap(circleBitmap, paddingLeft, paddingTop, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(squareBitmap, paddingLeft, paddingTop, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveLayerCount)
    }
}