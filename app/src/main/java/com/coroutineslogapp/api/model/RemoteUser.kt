package com.coroutineslogapp.api.model

import com.google.gson.annotations.SerializedName

data class RemoteUser(
    @SerializedName("login")
    var username: String,
    @SerializedName("avatar_url")
    var avatarUrl: String?,
    @SerializedName("bio")
    var biography: String?,
    var location: String?,
    var email: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("public_repos")
    var publicRepos: Int?,
    @SerializedName("total_private_repos")
    var privateRepos: Int?
)
