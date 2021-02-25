package com.coroutineslogapp.data.repository

import com.coroutineslogapp.api.GitHubService
import com.coroutineslogapp.cache.dao.UserDao
import com.coroutineslogapp.data.mappers.GetCredentials
import com.coroutineslogapp.data.mappers.mapToCacheUserModel
import com.coroutineslogapp.data.mappers.mapToDomainUserModel
import com.coroutineslogapp.data.prefs.SharedPrefsHandler
import com.coroutineslogapp.domain.model.User
import com.coroutineslogapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val service: GitHubService,
    private val userDao: UserDao,
    private val sharedPrefsHandler: SharedPrefsHandler
) : UserRepository {

    override suspend fun getRemoteUser(
        username: String,
        accessToken: String
    ): Flow<DomainUserResponse> =
        flow {
            val credentials = GetCredentials().invoke(username, accessToken)
            val response = withContext(Dispatchers.IO) {
                service.getUser(credentials)
            }
            sharedPrefsHandler.writeToken(credentials)
            emit(UserResponseHandler().check(response))
        }

    override suspend fun saveUserLocally(user: User) {
        userDao.insert(user.mapToCacheUserModel())
        sharedPrefsHandler.writeCurrentUsername(user.username)
    }

    override suspend fun getUserFromDatabase() =
        userDao.getUser(sharedPrefsHandler.readCurrentUsername())?.mapToDomainUserModel()


    override suspend fun getRemoteUserByStoredToken(): Flow<DomainUserResponse> =
        flow {
            val response = withContext(Dispatchers.IO) {
                service.getUser(authorization = sharedPrefsHandler.readToken())
            }
            emit(UserResponseHandler().check(response))
        }

    override suspend fun logout() {
        withContext(Dispatchers.IO) {
            sharedPrefsHandler.clearAll()
            userDao.deleteAll()
        }
    }
}
