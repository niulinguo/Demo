package com.lingo.ui.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.lingo.ui.R
import com.lingo.ui.utils.Utils
import com.lingo.ui.utils.dp

class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmapSize = 200.dp
    private val bitmapPadding = 100.dp
    private val bitmap = Utils.getBitmap(context, R.drawable.avatar, bitmapSize.toInt())

    private val camera = Camera()

    private var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        camera.setLocation(0f, 0f, -6 * Resources.getSystem().displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        val bitmapCenter = bitmapPadding + bitmapSize / 2

        canvas.withSave {
            canvas.translate(bitmapCenter, bitmapCenter)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(
                -bitmapSize,
                -bitmapSize,
                bitmapSize,
                0f,
            )
            canvas.rotate(flipRotation)
            canvas.translate(-bitmapCenter, -bitmapCenter)
            canvas.drawBitmap(bitmap, bitmapPadding, bitmapPadding, paint)
        }

        canvas.withSave {
            canvas.translate(bitmapCenter, bitmapCenter)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(
                -bitmapSize,
                0f,
                bitmapSize,
                bitmapSize,
            )
            canvas.rotate(flipRotation)
            canvas.translate(-bitmapCenter, -bitmapCenter)
            canvas.drawBitmap(bitmap, bitmapPadding, bitmapPadding, paint)
        }
    }
}