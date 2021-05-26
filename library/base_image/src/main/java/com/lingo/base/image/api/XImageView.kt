package com.lingo.base.image.api

import android.widget.ImageView
import com.lingo.base.image.core.option.AnimateOption
import com.lingo.base.image.core.option.HolderOption
import com.lingo.base.image.core.option.SizeOption
import com.lingo.base.image.core.option.ThumbnailOption

data class XImageView<T>(
    val url: T,
    val imageView: ImageView,
    val sizeOption: SizeOption?,
    val holderOption: HolderOption,
    val animateOption: AnimateOption?,
    val thumbnailOption: ThumbnailOption?
) {

    companion object {
        fun <T> builder(): Builder<T> {
            return Builder()
        }
    }

    class Builder<T> {
        private var url: T? = null
        private var imageView: ImageView? = null
        private var sizeOption: SizeOption? = null
        private var holderOption: HolderOption? = null
        private var animateOption: AnimateOption? = null
        private var thumbnailOption: ThumbnailOption? = null

        fun setUrl(url: T): Builder<T> {
            this.url = url
            return this
        }

        fun setImageView(imageView: ImageView): Builder<T> {
            this.imageView = imageView
            return this
        }

        fun setSizeOption(sizeOption: SizeOption): Builder<T> {
            this.sizeOption = sizeOption
            return this
        }

        fun setHolderOption(holderOption: HolderOption): Builder<T> {
            this.holderOption = holderOption
            return this
        }

        fun setAnimateOption(animateOption: AnimateOption): Builder<T> {
            this.animateOption = animateOption
            return this
        }

        fun setThumbnailOption(thumbnailOption: ThumbnailOption): Builder<T> {
            this.thumbnailOption = thumbnailOption
            return this
        }

        fun build(): XImageView<T> {
            val url = this.url ?: throw createNotNullException("url")
            val imageView =
                this.imageView ?: throw createNotNullException("imageView")
            val sizeOption = this.sizeOption
            val holderOption = this.holderOption ?: HolderOption()
            val animateOption = this.animateOption
            val thumbnailOption =
                this.thumbnailOption
            return XImageView(
                url,
                imageView,
                sizeOption,
                holderOption,
                animateOption,
                thumbnailOption
            )
        }

        private fun createNotNullException(name: String): Exception {
            return IllegalArgumentException("$name must not be null")
        }
    }
}