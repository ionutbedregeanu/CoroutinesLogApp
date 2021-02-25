package com.coroutineslogapp.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coroutineslogapp.cache.dao.RepositoryDao
import com.coroutineslogapp.cache.dao.UserDao
import com.coroutineslogapp.cache.model.CacheRepository
import com.coroutineslogapp.cache.model.CacheUser

@Database(entities = [CacheUser::class, CacheRepository::class], version = 1, exportSchema = false)
abstract class LogDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao
}
