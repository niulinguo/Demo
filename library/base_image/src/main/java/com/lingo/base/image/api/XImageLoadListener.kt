package com.lingo.base.image.api

import android.graphics.drawable.Drawable
import android.widget.ImageView

interface XImageLoadListener<T, R> {
    fun onLoadingComplete(uri: T, view: ImageView, resource: R)
    fun onLoadingError(source: T, e: Exception?)
    fun onLoadingStart(source: T, placeHolder: Drawable)
}