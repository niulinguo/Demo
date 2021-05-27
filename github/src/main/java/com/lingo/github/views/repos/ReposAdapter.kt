package com.lingo.github.views.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lingo.github.R
import com.lingo.github.base.BaseViewHolder
import com.lingo.github.databinding.ItemRepoBinding
import com.lingo.github.model.repo.Repo

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {
    private val dataList: MutableList<Repo> = mutableListOf()

    fun setDataList(dataList: List<Repo>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val rootView: View = inflater.inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bindItemData(dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class RepoViewHolder(itemView: View) : BaseViewHolder<Repo>(itemView) {
        private val binding: ItemRepoBinding = ItemRepoBinding.bind(itemView)

        override fun onBind(data: Repo, position: Int) {
            binding.model = data
        }
    }
}