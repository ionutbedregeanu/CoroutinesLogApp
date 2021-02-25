package com.coroutineslogapp.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coroutineslogapp.api.model.RemoteUser

@Entity(tableName = "repositories")
data class CacheRepository(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val fullName: String?,
    @Embedded(prefix = "owner_")
    val user: RemoteUser,
    val description: String?,
    val isPrivate: Boolean,
    val pushedAt: String?,
    val createdAt: String?,
    val updatedAt: String?,
)
