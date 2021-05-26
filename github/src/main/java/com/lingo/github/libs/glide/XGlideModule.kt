package com.lingo.github.libs.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

class XGlideModule : AppGlideModule() {
    companion object {
        private const val DISK_CACHE_NAME = "glide_image"
        private const val MB = 1024 * 1024L
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        builder.setDefaultRequestOptions {
            RequestOptions.formatOf(DecodeFormat.PREFER_ARGB_8888)
        }

        val maxMemory: Long = Runtime.getRuntime().maxMemory()
        val memoryCacheSize: Long = maxMemory / 8

        builder.setMemoryCache(LruResourceCache(memoryCacheSize))
        builder.setBitmapPool(LruBitmapPool(memoryCacheSize))

        val diskCacheSize: Long = 30 * MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, DISK_CACHE_NAME, diskCacheSize))
    }
}