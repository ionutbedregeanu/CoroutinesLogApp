package com.coroutineslogapp.data.repository

import com.coroutineslogapp.api.GitHubService
import com.coroutineslogapp.data.mappers.GetCredentials
import com.coroutineslogapp.data.prefs.SharedPrefsHandler
import com.coroutineslogapp.domain.model.User
import com.coroutineslogapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val service: GitHubService,
    private val sharedPrefsHandler: SharedPrefsHandler
) : UserRepository {

    override suspend fun getRemoteUser(
        username: String,
        accessToken: String
    ): Flow<DomainUserResponse> =
        flow {
            val response = withContext(Dispatchers.IO) {
                service.getUser(GetCredentials().invoke(username, accessToken))
            }
            emit(UserResponseHandler().check(response))
        }

    override suspend fun saveUserLocally(user: User) {
        sharedPrefsHandler.writeUser(user)
    }

    override suspend fun getPrefsUser() = sharedPrefsHandler.readUser()
}
