package com.lingo.github.model.permission

data class Permissions(
    val admin: Boolean,
    val push: Boolean,
    val pull: Boolean,
)
