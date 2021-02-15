package com.coroutineslogapp.data.repository

import com.coroutineslogapp.domain.model.User

sealed class DomainUserResponse {
    data class Success(val user: User) : DomainUserResponse()
    object Error : DomainUserResponse()
}
