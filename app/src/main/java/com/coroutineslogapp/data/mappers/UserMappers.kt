package com.coroutineslogapp.data.mappers

import com.coroutineslogapp.api.model.RemoteUser
import com.coroutineslogapp.cache.model.CacheUser
import com.coroutineslogapp.domain.model.User

fun RemoteUser.mapToDomainUserModel() = User(
    username = this.username,
    avatarUrl = this.avatarUrl,
    biography = this.biography,
    location = this.location,
    email = this.email,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    publicRepos = this.publicRepos,
    privateRepos = this.privateRepos
)

fun User.mapToCacheUserModel() = CacheUser(
    username = this.username,
    avatarUrl = this.avatarUrl,
    biography = this.biography,
    location = this.location,
    email = this.email,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    publicRepos = this.publicRepos,
    privateRepos = this.privateRepos
)

fun CacheUser.mapToDomainUserModel() = User(
    username = this.username,
    avatarUrl = this.avatarUrl,
    biography = this.biography,
    location = this.location,
    email = this.email,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    publicRepos = this.publicRepos,
    privateRepos = this.privateRepos
)
