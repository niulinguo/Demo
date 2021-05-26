package com.lingo.base.image

import android.widget.ImageView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lingo.base.image.api.XImageView
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class XImageViewTest {
    @Test
    fun create() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val url = "http://www.baidu.com"

        val xImageView = XImageView.builder<String>()
            .setUrl(url)
            .setImageView(ImageView(appContext))
            .build()

        assertEquals(xImageView.url, url)
    }
}