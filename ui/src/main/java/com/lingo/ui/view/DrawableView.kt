package com.lingo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.lingo.ui.drawable.MeshDrawable

class DrawableView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val drawable = MeshDrawable()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        drawable.setBounds(50, 50, width - 50, height - 50)
    }

    override fun onDraw(canvas: Canvas) {
        drawable.draw(canvas)
    }
}