package com.lingo.ui.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.lingo.ui.R
import com.lingo.ui.utils.dp

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val labelTextSize = 12.dp
    private val textMargin = 8.dp
    private val horizontalOffset = 5.dp
    private val verticalOffset = labelTextSize + 12.dp

    private val labelTextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    private var floatingLabelShown = false
    private var floatingLabelFraction = 0f
        private set(value) {
            field = value
            labelTextPaint.alpha = (value * 0xff).toInt()
            invalidate()
        }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    var useFloatingLabel = false
        set(value) {
            if (field == value) {
                return
            }
            field = value
            if (value) {
                setPadding(
                    paddingLeft,
                    (paddingTop + labelTextSize + textMargin).toInt(),
                    paddingRight,
                    paddingBottom
                )
            } else {
                setPadding(
                    paddingLeft,
                    (paddingTop - labelTextSize - textMargin).toInt(),
                    paddingRight,
                    paddingBottom
                )
            }
        }

    init {
        labelTextPaint.textSize = labelTextSize
        labelTextPaint.alpha = (floatingLabelFraction * 0xff).toInt()

        attrs.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.MaterialEditText)
            useFloatingLabel = ta.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
            ta.recycle()
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (useFloatingLabel) {
            if (floatingLabelShown && text.isNullOrEmpty()) {
                floatingLabelShown = false
                // 隐藏动画
                animator.reverse()
            } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
                floatingLabelShown = true
                // 显示动画
                animator.start()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (useFloatingLabel) {
            val verticalPix = verticalOffset + 16.dp * (1 - floatingLabelFraction)
            canvas.drawText(hint.toString(), horizontalOffset, verticalPix, labelTextPaint)
        }
    }
}