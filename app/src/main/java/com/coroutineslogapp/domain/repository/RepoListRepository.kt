package com.coroutineslogapp.domain.repository

import com.coroutineslogapp.data.repository.DomainRepositoriesResponse
import com.coroutineslogapp.domain.model.Repository
import com.coroutineslogapp.domain.model.TypeOfRepositoryList
import kotlinx.coroutines.flow.Flow

interface RepoListRepository {

    suspend fun getRepositories(
        typeOfRepositoryList: TypeOfRepositoryList,
        affiliation: String,
        sort: String
    ): Flow<DomainRepositoriesResponse>

    fun getStoredAffiliationIndices(): List<Int>

    fun getStoredSortTypeIndices(): Int

    fun saveSettings(affiliationIndices: List<Int>, sortTypeIndex: Int)

    suspend fun saveRepositoriesLocally(repositories: List<Repository>)

    fun getRepositoryFromDatabaseById(repositoryId: Long): Flow<Repository>
}
