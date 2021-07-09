package com.lingo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.lingo.ui.R
import com.lingo.ui.utils.Utils
import com.lingo.ui.utils.dp

class MultilineTextView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val showText =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis a porttitor eros, eget cursus libero. Nullam maximus condimentum justo, vel finibus justo ornare ut. Curabitur gravida leo ut justo dapibus ornare eu et nisi. Praesent dapibus sit amet felis vitae fermentum. Vestibulum malesuada magna eget turpis consequat imperdiet. Morbi tortor sem, volutpat id lacinia ut, pulvinar ac metus. Nulla ac pretium nunc. Suspendisse ultricies leo risus, quis faucibus sem consequat eu. Quisque cursus felis vitae diam finibus, a interdum nibh volutpat. Nunc a blandit magna. Nam blandit nisi id tellus semper, ut rutrum enim iaculis. Curabitur rhoncus nisi placerat posuere volutpat. Ut vitae risus cursus, bibendum dolor cursus, porttitor turpis.\n" +
                "\n" +
                "Phasellus scelerisque vitae felis eget pulvinar. Aenean sit amet molestie nisi. In blandit non purus a lacinia. Donec quis molestie justo, at vestibulum nisl. Maecenas aliquet eget quam nec varius. Integer ac nisl vel diam commodo luctus ut id sapien. Fusce turpis mauris, commodo ac malesuada et, faucibus ac nunc. Proin ante tellus, placerat varius purus nec, pretium commodo magna. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aenean nisl nulla, condimentum vel scelerisque nec, posuere sit amet turpis. Vivamus ultrices lacus eu iaculis condimentum. Pellentesque iaculis arcu a lacinia fringilla. Etiam et cursus nunc. Morbi pharetra rutrum vestibulum.\n" +
                "\n" +
                "Nulla tellus risus, pretium eget eleifend quis, condimentum dignissim ex. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Phasellus interdum neque sed leo aliquam rhoncus. Suspendisse ut tempus nulla. Integer at semper tortor. Aliquam mattis suscipit diam sit amet tempor. Nullam lorem lorem, tincidunt a feugiat sit amet, aliquam convallis odio. Integer ultricies enim sem, et vehicula felis pharetra in. Morbi nulla turpis, vestibulum quis sem ut, dictum rutrum nisl. Cras a purus ut metus commodo tristique id nec magna. Vivamus odio ante, porttitor vitae erat non, suscipit convallis nunc. Proin molestie pharetra dui, vel consequat orci convallis et. Fusce ullamcorper odio quis cursus suscipit. Curabitur pulvinar porta congue.\n" +
                "\n" +
                "Fusce egestas a massa a finibus. Mauris lobortis purus sit amet lacus accumsan blandit sit amet et massa. Sed vehicula convallis quam, id congue quam posuere vel. Duis consequat dolor id tincidunt iaculis. Pellentesque laoreet quam tempor, consectetur nunc vel, viverra tortor. Cras ut blandit justo, quis feugiat purus. Suspendisse vitae odio ultricies, vestibulum dolor vel, faucibus tortor. Phasellus vestibulum enim id lacus tempor malesuada.\n" +
                "\n" +
                "Phasellus consequat semper congue. Fusce nisi nisi, auctor id ante nec, scelerisque lobortis enim. Vestibulum rutrum ultricies semper. Duis risus libero, egestas id mauris ac, mollis hendrerit odio. Sed vel placerat lorem. Etiam malesuada vitae dui a dignissim. Curabitur vitae enim et mi porttitor lobortis. Nullam ut tellus vehicula, commodo elit eget, semper metus. Vivamus a lectus ac mauris ultricies aliquet sed congue arcu. Cras odio mi, tristique eget elit vitae, tempor tincidunt lectus."
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            this.textSize = 30.dp
            this.typeface = ResourcesCompat.getFont(context, R.font.zen_loop)
        }
    private lateinit var staticLayout: StaticLayout

    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val bitmapSize = 150.dp
    private val bitmap = Utils.getBitmap(context, R.drawable.avatar, bitmapSize.toInt())
    private val bitmapOffsetY = 110.dp

    private val fontMetrics = Paint.FontMetrics()
        .apply {
            textPaint.getFontMetrics(this)
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        staticLayout = createStaticLayout(showText, textPaint, width)
    }

    override fun onDraw(canvas: Canvas) {
//        staticLayout.draw(canvas)
        canvas.drawBitmap(bitmap, width - bitmapSize, bitmapOffsetY, paint)
        var start = 0
        var offsetY = 0f - fontMetrics.top
        while (start < showText.length) {
            val maxWidth =
                if (isOverlap(offsetY)) width.toFloat() - bitmapSize else width.toFloat()
            val count = textPaint.breakText(
                showText,
                start,
                showText.length,
                true,
                maxWidth,
                floatArrayOf(0f)
            )
            val end = start + count
            canvas.drawText(showText, start, end, 0f, offsetY, textPaint)

            start = end
            offsetY += textPaint.fontSpacing
        }
    }

    private fun isOverlap(fontOffset: Float): Boolean {
        if (fontOffset + fontMetrics.bottom < bitmapOffsetY) {
            return false
        }
        if (fontOffset + fontMetrics.top > bitmapOffsetY + bitmapSize) {
            return false
        }
        return true
    }

    private fun createStaticLayout(
        source: CharSequence,
        paint: TextPaint,
        width: Int,
    ): StaticLayout =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(source, 0, source.length, paint, width)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(0f, 1f)
                .setIncludePad(false)
                .build()
        } else {
            StaticLayout(source, paint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
        }
}