package com.coroutineslogapp.ui.mappers

import com.coroutineslogapp.domain.model.User
import com.coroutineslogapp.ui.model.User as UIUser

fun User.mapToUIModel() = UIUser(
    username = this.username,
    avatarUrl = this.avatarUrl ?: "",
    biography = this.biography ?: "",
    location = this.location ?: "",
    email = this.email ?: "",
    createdAt = this.createdAt ?: "",
    updatedAt = this.updatedAt ?: "",
    publicRepos = this.publicRepos ?: 0,
    privateRepos = this.privateRepos ?: 0
)

