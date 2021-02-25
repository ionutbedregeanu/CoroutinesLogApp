package com.coroutineslogapp.domain.model

import com.coroutineslogapp.api.model.RemoteUser

data class Repository(
    val id: Long,
    val name: String?,
    val fullName: String?,
    val user: RemoteUser,
    val description: String?,
    val isPrivate: Boolean,
    val pushedAt: String?,
    val createdAt: String?,
    val updatedAt: String?,
)
