package com.coroutineslogapp.ui.mappers

import com.coroutineslogapp.domain.model.Repository
import com.coroutineslogapp.ui.model.RepositoryDetails
import com.coroutineslogapp.ui.model.RepositoryItem

fun List<Repository>?.mapToUIRepositories() = this?.map { repository ->
    RepositoryItem(
        id = repository.id,
        name = repository.name ?: ""
    )
}

fun Repository.mapToRepositoryDetails() =
    RepositoryDetails(
        description = this.description ?: "",
        isPrivate = this.isPrivate
    )
