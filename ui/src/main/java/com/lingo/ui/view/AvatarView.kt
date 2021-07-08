package com.lingo.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lingo.ui.R
import com.lingo.ui.utils.Utils
import com.lingo.ui.utils.dp

private val IMAGE_WIDTH = 200f.dp
private val IMAGE_PADDING = 20f.dp

class AvatarView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val bounds = RectF(
        IMAGE_PADDING,
        IMAGE_PADDING,
        IMAGE_PADDING + IMAGE_WIDTH,
        IMAGE_PADDING + IMAGE_WIDTH,
    )

    override fun onDraw(canvas: Canvas) {
        val saveLayerCount = canvas.saveLayer(bounds, null)
        canvas.drawOval(
            bounds,
            paint,
        )
        paint.xfermode = xfermode
        canvas.drawBitmap(
            Utils.getBitmap(context, R.drawable.avatar, IMAGE_WIDTH.toInt()),
            IMAGE_PADDING,
            IMAGE_PADDING,
            paint,
        )
        paint.xfermode = null
        canvas.restoreToCount(saveLayerCount)
    }

}