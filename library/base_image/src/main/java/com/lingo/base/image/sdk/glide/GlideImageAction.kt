package com.lingo.base.image.sdk.glide

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.lingo.base.image.api.XImageLoadListener
import com.lingo.base.image.api.XImageView
import com.lingo.base.image.core.XImageActionBase
import com.lingo.base.image.core.XImageConfig
import com.lingo.base.image.sdk.glide.transformation.BlurTransformation
import java.util.concurrent.Executor

class GlideImageAction<T> : XImageActionBase<T> {
    private fun <T> requestBuilder(context: Context, url: T): RequestBuilder<Drawable> {
        return Glide.with(context)
            .load(url)
    }

    override fun loadImage(context: Context, imageView: XImageView<out T>) {
        requestBuilder(context, imageView.url)
            .into(imageView.imageView)
    }

    override fun loadImageWithHolder(context: Context, imageView: XImageView<out T>) {
        requestBuilder(context, imageView.url)
            .error(imageView.holderOption.errorHolder)
            .placeholder(imageView.holderOption.placeHolder)
            .into(imageView.imageView)
    }

    override fun loadImageWithThumbnail(context: Context, imageView: XImageView<out T>) {
        val thumbnailUrl: String? = imageView.thumbnailOption?.url
        if (thumbnailUrl == null) {
            requestBuilder(context, imageView.url)
                .thumbnail(XImageConfig.SIZE_MULTIPLIER)
                .into(imageView.imageView)
        } else {
            requestBuilder(context, imageView.url)
                .thumbnail(
                    requestBuilder(context, thumbnailUrl)
                )
                .into(imageView.imageView)
        }
    }

    override fun loadImageWithSize(context: Context, imageView: XImageView<out T>) {
        val sizeOption = imageView.sizeOption
        if (sizeOption == null) {
            loadImage(context, imageView)
        } else {
            requestBuilder(context, imageView.url)
                .override(sizeOption.width, sizeOption.height)
                .into(imageView.imageView)
        }
    }

    override fun loadImageWithAnim(context: Context, imageView: XImageView<out T>) {
        loadImage(context, imageView)
    }

    override fun loadImageWithCache(context: Context, imageView: XImageView<out T>) {
        requestBuilder(context, imageView.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // TODO: 抽离
            .skipMemoryCache(true)
            .into(imageView.imageView)
    }

    override fun loadCircleImage(context: Context, imageView: XImageView<out T>) {
        requestBuilder(context, imageView.url)
            .dontAnimate()
            .transform(CircleCrop())
            .into(imageView.imageView)
    }

    override fun loadBlurImage(context: Context, imageView: XImageView<out T>) {
        requestBuilder(context, imageView.url)
            .dontAnimate()
            .transform(BlurTransformation(context))
            .into(imageView.imageView)
    }

    override fun loadGifImage(context: Context, imageView: XImageView<out T>) {
        Glide.with(context)
            .asGif()
            .load(imageView.url)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView.imageView)
    }

    override fun loadImageListener(
        context: Context,
        imageView: XImageView<out T>,
        listener: XImageLoadListener<T, Drawable>
    ) {
        requestBuilder(context, imageView.url)
            .error(imageView.holderOption.errorHolder)
            .placeholder(imageView.holderOption.placeHolder)
            .listener(GlideListener(listener, imageView.url, imageView.imageView))
            .into(imageView.imageView)
    }

    override fun cancelAllTasks(context: Context) {
        Glide.with(context).pauseRequests()
    }

    override fun resumeAllTasks(context: Context) {
        Glide.with(context).resumeRequests()
    }

    override fun getCacheSize(context: Context): Long {
        return 0
    }

    override fun clearAllCache(context: Context, executor: Executor) {
        clearDiskCache(context, executor)
        clearMemory(context)
    }

    private fun clearDiskCache(context: Context, executor: Executor) {
        executor.execute {
            Glide.get(context).clearDiskCache()
        }
    }

    private fun clearMemory(context: Context) {
        Glide.get(context).clearMemory()
    }
}