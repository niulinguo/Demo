package com.lingo.base.image.sdk.glide

import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lingo.base.image.api.XImageLoadListener

class GlideListener<R, T>(
    private val imageLoadListener: XImageLoadListener<T, R>,
    private val url: T,
    private val view: ImageView
) :
    RequestListener<R> {

    override fun onLoadFailed(
        e: GlideException?,
        model: Any,
        target: Target<R>,
        isFirstResource: Boolean
    ): Boolean {
        imageLoadListener.onLoadingError(url, e)
        return false
    }

    override fun onResourceReady(
        resource: R,
        model: Any,
        target: Target<R>,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean {
        imageLoadListener.onLoadingComplete(url, view, resource)
        return false
    }
}