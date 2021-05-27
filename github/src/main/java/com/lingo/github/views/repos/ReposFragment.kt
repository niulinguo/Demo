package com.lingo.github.views.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lingo.github.base.BaseFragment
import com.lingo.github.databinding.FragmentReposBinding

class ReposFragment : BaseFragment() {
    private val viewModel: ReposViewModel by lazy {
        ViewModelProvider(this).get(ReposViewModel::class.java)
    }

    private lateinit var binding: FragmentReposBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchReposAsync()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentReposBinding.inflate(layoutInflater, container, false)
        initList(binding.list)
        return binding.root
    }

    private fun initList(listView: RecyclerView) {
        val adapter = ReposAdapter()
        listView.adapter = adapter

        viewModel.repos.observe(viewLifecycleOwner, {
            adapter.setDataList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}