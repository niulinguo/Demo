package com.lingo.github.views.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.lingo.github.R
import com.lingo.github.databinding.ItemRepoBinding
import com.lingo.github.model.repo.Repo
import com.lingo.github.performance.TimeRecorder
import com.lingo.github.performance.list.FirstItemBindCallbackViewHolder

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

    class RepoViewHolder(itemView: View) :
        FirstItemBindCallbackViewHolder<Repo>(itemView, { view, _ ->
            view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    view.viewTreeObserver.removeOnPreDrawListener(this)
                    TimeRecorder.endRecord("FeedShow")
                    return true
                }
            })
        }) {
        private val binding: ItemRepoBinding = ItemRepoBinding.bind(itemView)

        override fun onBind(data: Repo, position: Int) {
            binding.model = data
        }
    }
}