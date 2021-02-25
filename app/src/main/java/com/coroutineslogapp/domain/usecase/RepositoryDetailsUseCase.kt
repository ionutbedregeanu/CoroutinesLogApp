package com.coroutineslogapp.domain.usecase

import com.coroutineslogapp.domain.repository.RepoListRepository
import javax.inject.Inject

class RepositoryDetailsUseCase @Inject constructor(private val repository: RepoListRepository) {
    operator fun invoke(repositoryId: Long)  = repository.getRepositoryFromDatabaseById(repositoryId)
}
