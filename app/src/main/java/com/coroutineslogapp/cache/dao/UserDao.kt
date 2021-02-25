package com.coroutineslogapp.cache.dao

import androidx.room.*
import com.coroutineslogapp.cache.model.CacheUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: CacheUser)

    @Query("SELECT * from users WHERE username = :username")
    suspend fun getUser(username: String): CacheUser?

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}
