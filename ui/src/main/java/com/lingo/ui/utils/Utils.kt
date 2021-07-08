package com.lingo.ui.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull

object Utils {
    fun dp2px(value: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        Resources.getSystem().displayMetrics
    )

    fun getBitmap(@NonNull context: Context, @DrawableRes resId: Int, width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, resId, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(context.resources, resId, options)
    }
}