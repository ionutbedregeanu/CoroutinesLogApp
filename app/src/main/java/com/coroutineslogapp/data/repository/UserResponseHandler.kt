package com.coroutineslogapp.data.repository

import com.coroutineslogapp.data.mappers.mapToDomainUserModel
import com.coroutineslogapp.api.model.RemoteUser
import retrofit2.Response

class UserResponseHandler {
    fun check(response: Response<RemoteUser>): DomainUserResponse {
        return when {
            response.isSuccessful && response.body() != null -> DomainUserResponse.Success((response.body() as RemoteUser).mapToDomainUserModel())
            else -> DomainUserResponse.Error
        }
    }
}
