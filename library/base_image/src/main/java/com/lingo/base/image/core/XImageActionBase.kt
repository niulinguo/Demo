package com.lingo.base.image.core

import android.content.Context
import android.graphics.drawable.Drawable
import com.lingo.base.image.api.XImageLoadListener
import com.lingo.base.image.api.XImageView
import java.util.concurrent.Executor

interface XImageActionBase<T> {
    fun loadImage(context: Context, imageView: XImageView<out T>)
    fun loadImageWithHolder(context: Context, imageView: XImageView<out T>)
    fun loadImageWithThumbnail(context: Context, imageView: XImageView<out T>)
    fun loadImageWithSize(context: Context, imageView: XImageView<out T>)
    fun loadImageWithAnim(context: Context, imageView: XImageView<out T>)
    fun loadImageWithCache(context: Context, imageView: XImageView<out T>)
    fun loadCircleImage(context: Context, imageView: XImageView<out T>)
    fun loadBlurImage(context: Context, imageView: XImageView<out T>)
    fun loadGifImage(context: Context, imageView: XImageView<out T>)
    fun loadImageListener(
        context: Context,
        imageView: XImageView<out T>,
        listener: XImageLoadListener<T, Drawable>
    )

    fun cancelAllTasks(context: Context)
    fun resumeAllTasks(context: Context)
    fun getCacheSize(context: Context): Long
    fun clearAllCache(context: Context, executor: Executor)
}