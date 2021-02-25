package com.coroutineslogapp.data.repository

import com.coroutineslogapp.domain.model.Repository

sealed class DomainRepositoriesResponse {
    data class Success(val repositories: List<Repository>) : DomainRepositoriesResponse()
    object Error : DomainRepositoriesResponse()
}
