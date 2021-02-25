package com.coroutineslogapp.domain.usecase

import com.coroutineslogapp.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.logout()
}
