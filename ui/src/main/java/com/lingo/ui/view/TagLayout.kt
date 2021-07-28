package com.lingo.ui.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val childrenBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)

        var widthUsed = 0
        var heightUsed = 0
        var lineMaxHeight = 0
        var lineMaxWidth = 0

        for ((index, child) in children.withIndex()) {
            val lp = child.layoutParams as MarginLayoutParams

            measureChildWithMargins(
                child,
                0,
                widthUsed,
                0,
                heightUsed
            )

            if (widthSpecMode != MeasureSpec.UNSPECIFIED && widthUsed + child.measuredWidth > widthSpecSize) {
                // 换行
                widthUsed = 0
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
            }

            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBounds = childrenBounds[index]
            val left = widthUsed + lp.leftMargin
            val right = left + child.measuredWidth
            val top = heightUsed
            val bottom = top + child.measuredHeight
            childBounds.set(
                left,
                top,
                right,
                bottom,
            )

            widthUsed = right + lp.rightMargin
            lineMaxWidth = max(lineMaxWidth, widthUsed)
        }

        heightUsed += lineMaxHeight

        val selfWidth = resolveSize(lineMaxWidth, widthMeasureSpec)
        val selfHeight = resolveSize(heightUsed, heightMeasureSpec)
        setMeasuredDimension(selfWidth, selfHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val childBounds = childrenBounds[index]
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }
}