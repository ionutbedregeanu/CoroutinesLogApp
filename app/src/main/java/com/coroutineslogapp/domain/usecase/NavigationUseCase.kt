package com.coroutineslogapp.domain.usecase

import com.coroutineslogapp.data.repository.DomainUserResponse
import com.coroutineslogapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NavigationUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun shouldNavigateToNextScreen(username: String, accessToken: String): Boolean {
        var shouldNavigate = false
        userRepository.getRemoteUser(username, accessToken).collect { requestResponse ->
            shouldNavigate = when (requestResponse) {
                is DomainUserResponse.Success -> {
                    userRepository.saveUserLocally(requestResponse.user)
                    true
                }
                is DomainUserResponse.Error -> false
            }
        }
        return shouldNavigate
    }
}
