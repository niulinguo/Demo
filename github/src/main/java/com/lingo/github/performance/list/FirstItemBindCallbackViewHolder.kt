package com.lingo.github.performance.list

import android.view.View
import com.lingo.github.base.BaseViewHolder

abstract class FirstItemBindCallbackViewHolder<D>(
    itemView: View,
    private val callback: (view: View, data: D) -> Unit
) : BaseViewHolder<D>(itemView) {

    private var hasRecorded = false

    override fun bindItemData(data: D, position: Int) {
        super.bindItemData(data, position)

        if (!hasRecorded && position == 0) {
            hasRecorded = true
            callback(itemView, data)
        }
    }

}