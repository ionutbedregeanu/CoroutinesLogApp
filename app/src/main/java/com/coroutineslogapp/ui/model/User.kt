package com.coroutineslogapp.ui.model

data class User(
    val username: String = "",
    val avatarUrl: String = "",
    val biography: String = "",
    val location: String = "",
    val email: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val publicRepos: Int = 0,
    val privateRepos: Int = 0
)
