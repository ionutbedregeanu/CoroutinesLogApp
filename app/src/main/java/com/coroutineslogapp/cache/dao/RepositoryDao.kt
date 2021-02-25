package com.coroutineslogapp.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coroutineslogapp.cache.model.CacheRepository

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositories: List<CacheRepository>)

    @Query("SELECT * FROM repositories WHERE id=:repoId")
    suspend fun getRepositoryById(repoId: Long): CacheRepository
}
