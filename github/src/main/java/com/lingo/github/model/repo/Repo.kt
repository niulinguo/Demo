package com.lingo.github.model.repo

data class Repo(
    val id: Long,
    val node_id: String,
    val name: String,
    val full_name: String,
    val private: Boolean
)
