package com.lingo.github

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.lingo.base.image.api.XImageLoader
import com.lingo.base.image.api.XImageView
import com.lingo.github.base.BaseActivity
import com.lingo.github.utils.TimeRecorder

class MainActivity : BaseActivity() {
    private val imageView: ImageView by lazy {
        findViewById(R.id.image_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TimeRecorder.endRecord()
    }

    fun fetchImage(view: View) {
        XImageLoader.loadBlurImage(
            this,
            XImageView.builder<String>()
                .setUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201401%2F04%2F114458foyo99odqb8qjzg4.jpg&refer=http%3A%2F%2Fattach.bbs.miui.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624610021&t=f4968a4e517bf9c944f2ed83f54eb496")
                .setImageView(imageView)
                .build()
        )
    }
}