package com.lingo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.lingo.ui.R
import com.lingo.ui.utils.Utils
import com.lingo.ui.utils.dp

class ClipView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmapSize = 200.dp
    private val bitmapPadding = 100.dp
    private val bitmap = Utils.getBitmap(context, R.drawable.avatar, bitmapSize.toInt())
    private val bitmapRect = RectF(
        bitmapPadding,
        bitmapPadding,
        bitmapPadding + bitmapSize,
        bitmapPadding + bitmapSize,
    )

    private val clipped = Path()
        .apply {
            this.addOval(bitmapRect, Path.Direction.CCW)
        }

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(clipped)
        canvas.drawBitmap(bitmap, bitmapPadding, bitmapPadding, paint)
    }
}