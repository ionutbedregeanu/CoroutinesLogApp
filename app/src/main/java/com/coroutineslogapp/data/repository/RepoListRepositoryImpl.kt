package com.coroutineslogapp.data.repository

import com.coroutineslogapp.api.GitHubService
import com.coroutineslogapp.cache.dao.RepositoryDao
import com.coroutineslogapp.data.mappers.mapToCacheRepositories
import com.coroutineslogapp.data.mappers.mapToDomainRepository
import com.coroutineslogapp.data.prefs.SharedPrefsHandler
import com.coroutineslogapp.domain.model.Repository
import com.coroutineslogapp.domain.model.TypeOfRepositoryList
import com.coroutineslogapp.domain.repository.RepoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RepoListRepositoryImpl(
    private val service: GitHubService,
    private val repositoryDao: RepositoryDao,
    private val sharedPrefsHandler: SharedPrefsHandler
) : RepoListRepository {

    override suspend fun getRepositories(
        typeOfRepositoryList: TypeOfRepositoryList,
        affiliation: String,
        sort: String
    ) = flow {
        val response = withContext(Dispatchers.IO) {
            service.getRepositories(
                sharedPrefsHandler.readToken(),
                typeOfRepositoryList.toString(),
                affiliation,
                sort
            )
        }
        emit(RepoResponseHandler().check(response))
    }

    override fun getStoredAffiliationIndices() = sharedPrefsHandler.readAffiliation()

    override fun getStoredSortTypeIndices() = sharedPrefsHandler.readSortType()

    override fun saveSettings(affiliationIndices: List<Int>, sortTypeIndex: Int) {
        sharedPrefsHandler.writeAffiliation(affiliationIndices)
        sharedPrefsHandler.writeSortType(sortTypeIndex)
    }

    override suspend fun saveRepositoriesLocally(repositories: List<Repository>) {
        repositoryDao.insert(repositories.mapToCacheRepositories())
    }

    override fun getRepositoryFromDatabaseById(repositoryId: Long) = flow {
        val response = withContext(Dispatchers.IO) {
            repositoryDao.getRepositoryById(repositoryId).mapToDomainRepository()
        }
        emit(response)
    }
}
