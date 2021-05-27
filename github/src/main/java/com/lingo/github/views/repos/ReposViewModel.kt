package com.lingo.github.views.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lingo.github.api.Repos
import com.lingo.github.base.BaseViewModel
import com.lingo.github.libs.retrofit.XRetrofit
import com.lingo.github.model.repo.Repo
import kotlinx.coroutines.launch

class ReposViewModel : BaseViewModel() {

    private val _repos: MutableLiveData<List<Repo>> = MutableLiveData()
    val repos: LiveData<List<Repo>> = _repos

    fun fetchReposAsync() {
        viewModelScope.launch {
            fetchRepos()
        }
    }

    private suspend fun fetchRepos() {
        val repos = XRetrofit.get(Repos::class.java).organizationRepos("alibaba")
        _repos.value = repos
    }
}