package com.lingo.github.model.license

data class License(
    val key: String,
    val name: String,
    val spdx_id: String,
    val url: String,
    val node_id: String,
)
