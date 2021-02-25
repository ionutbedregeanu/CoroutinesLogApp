package com.coroutineslogapp.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class CacheUser(
    @PrimaryKey
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
