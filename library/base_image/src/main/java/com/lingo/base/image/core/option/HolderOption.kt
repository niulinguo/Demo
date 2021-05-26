package com.lingo.base.image.core.option

import androidx.annotation.DrawableRes
import com.lingo.base.image.core.XImageConfig

data class HolderOption(
    @DrawableRes
    val placeHolder: Int = XImageConfig.NORMAL_PLACEHOLDER,
    @DrawableRes
    val errorHolder: Int = XImageConfig.NORMAL_PLACEHOLDER
)
