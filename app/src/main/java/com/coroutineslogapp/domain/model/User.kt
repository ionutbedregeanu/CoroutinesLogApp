package com.coroutineslogapp.domain.model

data class User(
    val username: String,
    val avatarUrl: String?,
    val biography: String?,
    val location: String?,
    val email: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val publicRepos: Int?,
    val privateRepos: Int?
)
