package com.coroutineslogapp.data.repository

import com.coroutineslogapp.api.model.RemoteRepository
import com.coroutineslogapp.data.mappers.mapToDomainRepositoriesList
import retrofit2.Response

class RepoResponseHandler {
    fun check(response: Response<List<RemoteRepository>>): DomainRepositoriesResponse {
        return when {
            response.isSuccessful && response.body() != null ->
                DomainRepositoriesResponse.Success((response.body() as List<RemoteRepository>).mapToDomainRepositoriesList())
            else -> DomainRepositoriesResponse.Error
        }
    }
}
