package com.coroutineslogapp.data.mappers

import com.coroutineslogapp.api.model.RemoteRepository
import com.coroutineslogapp.cache.model.CacheRepository
import com.coroutineslogapp.domain.model.Repository

fun List<RemoteRepository>.mapToDomainRepositoriesList() = this.map { remoteRepository ->
    Repository(
        id = remoteRepository.id,
        name = remoteRepository.name,
        fullName = remoteRepository.fullName,
        user = remoteRepository.owner,
        description = remoteRepository.description,
        isPrivate = remoteRepository.isPrivate,
        pushedAt = remoteRepository.pushedAt,
        createdAt = remoteRepository.createdAt,
        updatedAt = remoteRepository.updatedAt,
    )
}

fun CacheRepository.mapToDomainRepository() = Repository(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    user = this.user,
    description = this.description,
    isPrivate = this.isPrivate,
    pushedAt = this.pushedAt,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)

fun List<Repository>.mapToCacheRepositories() = this.map { domainRepository ->
    CacheRepository(
        id = domainRepository.id,
        name = domainRepository.name,
        fullName = domainRepository.fullName,
        user = domainRepository.user,
        description = domainRepository.description,
        isPrivate = domainRepository.isPrivate,
        pushedAt = domainRepository.pushedAt,
        createdAt = domainRepository.createdAt,
        updatedAt = domainRepository.updatedAt,
    )
}