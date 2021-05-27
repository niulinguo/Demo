package com.lingo.github.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItemData(data: D, position: Int) {
        onBind(data, position)
    }

    abstract fun onBind(data: D, position: Int)
}