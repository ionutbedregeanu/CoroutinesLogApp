package com.coroutineslogapp.api

import androidx.lifecycle.LiveData
import com.coroutineslogapp.api.model.RemoteRepository
import com.coroutineslogapp.api.model.RemoteUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GitHubService {

    @GET("user")
    suspend fun getUser(@Header("Authorization") authorization: String): Response<RemoteUser>

    @GET("user/repos")
    suspend fun getRepositories(
        @Header("Authorization") authorization: String,
        @Query("visibility") visibility: String,
        @Query("affiliation") affiliation: String,
        @Query("sort") sort: String
    ): Response<List<RemoteRepository>>
}
