package com.lingo.base.image.api

import android.content.Context
import android.graphics.drawable.Drawable
import com.lingo.base.image.core.XImageActionBase
import com.lingo.base.image.sdk.glide.GlideImageAction
import java.util.concurrent.Executor

object XImageLoader : XImageActionBase<Any> {
    private val proxy: XImageActionBase<Any> = GlideImageAction()

    override fun loadImage(context: Context, imageView: XImageView<out Any>) {
        proxy.loadImage(context, imageView)
    }

    override fun loadImageWithHolder(context: Context, imageView: XImageView<out Any>) {
        proxy.loadImageWithHolder(context, imageView)
    }

    override fun loadImageWithThumbnail(context: Context, imageView: XImageView<out Any>) {
        proxy.loadImageWithThumbnail(context, imageView)
    }

    override fun loadImageWithSize(context: Context, imageView: XImageView<out Any>) {
        proxy.loadImageWithSize(context, imageView)
    }

    override fun loadImageWithAnim(context: Context, imageView: XImageView<out Any>) {
        proxy.loadImageWithAnim(context, imageView)
    }

    override fun loadImageWithCache(context: Context, imageView: XImageView<out Any>) {
        proxy.loadImageWithCache(context, imageView)
    }

    override fun loadCircleImage(context: Context, imageView: XImageView<out Any>) {
        proxy.loadCircleImage(context, imageView)
    }

    override fun loadBlurImage(context: Context, imageView: XImageView<out Any>) {
        proxy.loadBlurImage(context, imageView)
    }

    override fun loadGifImage(context: Context, imageView: XImageView<out Any>) {
        proxy.loadGifImage(context, imageView)
    }

    override fun loadImageListener(
        context: Context,
        imageView: XImageView<out Any>,
        listener: XImageLoadListener<Any, Drawable>
    ) {
        proxy.loadImageListener(
            context, imageView, listener
        )
    }

    override fun cancelAllTasks(context: Context) {
        proxy.cancelAllTasks(context)
    }

    override fun resumeAllTasks(context: Context) {
        proxy.resumeAllTasks(context)
    }

    override fun getCacheSize(context: Context): Long {
        return proxy.getCacheSize(context)
    }

    override fun clearAllCache(context: Context, executor: Executor) {
        proxy.clearAllCache(context, executor)
    }
}