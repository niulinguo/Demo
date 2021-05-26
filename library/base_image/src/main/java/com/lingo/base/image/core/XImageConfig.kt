package com.lingo.base.image.core

import androidx.annotation.DrawableRes

object XImageConfig {
    /**
     * 是否使用 placeHolder
     */
    const val USE_PLACEHOLDER: Boolean = true

    /**
     * 是否使用 errorHolder
     */
    const val USE_ERRORHOLDER: Boolean = true

    const val LOAD_STRATEGY_NORMAL: Int = 0
    const val LOAD_STRATEGY_ONLY_WIFI: Int = 1

    @DrawableRes
    const val NORMAL_PLACEHOLDER: Int = android.R.drawable.picture_frame

    const val IMAGE_GLIDE: Int = 100
    const val IMAGE_FRESCO: Int = 101
    const val IMAGE_PICASSO: Int = 102

    /**
     * 是否使用缩略图
     */
    const val USE_THUMBNAIL: Boolean = true

    /**
     * 原始图像的10%位缩略图
     */
    const val SIZE_MULTIPLIER: Float = 0.1f

    const val LOAD_ANIMATE: Int = 0
}