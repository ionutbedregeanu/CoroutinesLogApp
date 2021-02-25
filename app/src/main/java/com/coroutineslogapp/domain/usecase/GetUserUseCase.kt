package com.coroutineslogapp.domain.usecase

import com.coroutineslogapp.data.repository.DomainUserResponse
import com.coroutineslogapp.domain.model.User
import com.coroutineslogapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUser(shouldRefresh: Boolean): Flow<User?> = flow {
        val localStoredUser = userRepository.getUserFromDatabase()
        if (localStoredUser != null && !shouldRefresh) {
            emit(localStoredUser)
        } else {
            userRepository.getRemoteUserByStoredToken().collect { response ->
                when (response) {
                    is DomainUserResponse.Success -> emit(response.user)
                    is DomainUserResponse.Error -> emit(null)
                }
            }
        }
    }
}
