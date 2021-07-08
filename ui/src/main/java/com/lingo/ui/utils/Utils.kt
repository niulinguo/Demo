package com.lingo.ui.utils

import android.content.res.Resources
import android.util.TypedValue

object Utils {
    fun dp2px(value: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        Resources.getSystem().displayMetrics
    )
}