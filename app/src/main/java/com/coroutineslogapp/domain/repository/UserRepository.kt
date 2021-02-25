package com.coroutineslogapp.domain.repository

import com.coroutineslogapp.data.repository.DomainUserResponse
import com.coroutineslogapp.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun getRemoteUser(username: String, accessToken: String): Flow<DomainUserResponse>
    suspend fun saveUserLocally(user: User)
    suspend fun getUserFromDatabase(): User?
    suspend fun getRemoteUserByStoredToken(): Flow<DomainUserResponse>
    suspend fun logout()
}
