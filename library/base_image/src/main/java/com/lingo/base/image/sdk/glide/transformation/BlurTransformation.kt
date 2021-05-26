package com.lingo.base.image.sdk.glide.transformation

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

class BlurTransformation(context: Context) : BitmapTransformation() {

    private val rs: RenderScript = RenderScript.create(context)

    companion object {
        private const val VERSION = 1
        private val ID =
            "${BlurTransformation::class.qualifiedName}.$VERSION"
        private val ID_BYTES = ID.toByteArray(CHARSET)
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val blurredBitmap: Bitmap = toTransform.copy(toTransform.config, true)

        val input: Allocation = Allocation.createFromBitmap(
            rs,
            blurredBitmap,
            Allocation.MipmapControl.MIPMAP_FULL,
            Allocation.USAGE_SHARED
        )

        val output: Allocation = Allocation.createTyped(rs, input.type)

        val script: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setInput(input)
        script.setRadius(10f)
        script.forEach(output)

        output.copyTo(blurredBitmap)

        toTransform.recycle()

        return blurredBitmap
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }

    override fun equals(other: Any?): Boolean {
        return other is BlurTransformation
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }
}