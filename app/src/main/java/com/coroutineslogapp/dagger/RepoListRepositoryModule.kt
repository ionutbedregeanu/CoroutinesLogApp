package com.coroutineslogapp.dagger

import com.coroutineslogapp.api.GitHubService
import com.coroutineslogapp.cache.dao.RepositoryDao
import com.coroutineslogapp.data.prefs.SharedPrefsHandler
import com.coroutineslogapp.data.repository.RepoListRepositoryImpl
import com.coroutineslogapp.domain.repository.RepoListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoListRepositoryModule {

    @Provides
    fun provideRepoListRepository(
        service: GitHubService,
        repositoryDao: RepositoryDao,
        sharedPrefsHandler: SharedPrefsHandler
    ): RepoListRepository = RepoListRepositoryImpl(service, repositoryDao, sharedPrefsHandler)
}
