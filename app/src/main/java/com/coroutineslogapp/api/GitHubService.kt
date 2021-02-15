package com.coroutineslogapp.api

import com.coroutineslogapp.api.model.RemoteUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GitHubService {

    @GET("user")
    suspend fun getUser(@Header("Authorization") authorization: String): Response<RemoteUser>
}
