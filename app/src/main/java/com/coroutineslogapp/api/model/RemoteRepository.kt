package com.coroutineslogapp.api.model

data class RemoteRepository(
    val id: Long,
    val name: String?,
    val fullName: String?,
    val owner: RemoteUser,
    val description: String?,
    val isPrivate: Boolean,
    val pushedAt: String?,
    val createdAt: String?,
    val updatedAt: String?,
)
