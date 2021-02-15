package com.coroutineslogapp.dagger

import com.coroutineslogapp.api.GitHubService
import com.coroutineslogapp.data.prefs.SharedPrefsHandler
import com.coroutineslogapp.data.repository.UserRepositoryImpl
import com.coroutineslogapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    fun provideUserRepository(
        gitHubService: GitHubService,
        sharedPrefsHandler: SharedPrefsHandler
    ): UserRepository = UserRepositoryImpl(gitHubService, sharedPrefsHandler)
}
