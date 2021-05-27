package com.lingo.github.api

import com.lingo.github.model.repo.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface Repos {
    @GET("/orgs/{organization}/repos")
    suspend fun organizationRepos(@Path("organization") organization: String): List<Repo>
}