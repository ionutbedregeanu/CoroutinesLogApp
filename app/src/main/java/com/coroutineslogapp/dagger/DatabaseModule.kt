package com.coroutineslogapp.dagger

import android.app.Application
import androidx.room.Room
import com.coroutineslogapp.cache.LogDatabase
import com.coroutineslogapp.cache.dao.RepositoryDao
import com.coroutineslogapp.cache.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val DATABASE_NAME = "log_app.db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDb(app: Application): LogDatabase {
        return Room
            .databaseBuilder(app, LogDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(db: LogDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideRepositoryDao(db: LogDatabase): RepositoryDao {
        return db.repositoryDao()
    }
}
