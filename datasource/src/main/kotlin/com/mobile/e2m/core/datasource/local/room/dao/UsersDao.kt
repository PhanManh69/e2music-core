package com.mobile.e2m.core.datasource.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.mobile.e2m.core.datasource.local.room.entity.UsersEntity

@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UsersEntity>

    @Query("SELECT username FROM users")
    suspend fun getUsername(): List<String>

    @Query("SELECT email FROM users")
    suspend fun getEmail(): List<String>

    @Query("SELECT * FROM users WHERE LOWER(username) = LOWER(:emailAccount) OR LOWER(email) = LOWER(:emailAccount)")
    suspend fun getUserByEmailOrUsername(emailAccount: String): List<UsersEntity>

    @Query("UPDATE users SET password = :newPassword WHERE id = :id")
    suspend fun updatePassword(id: Int, newPassword: String): Int

    @Upsert
    suspend fun insert(user: UsersEntity): Long

    @Update
    suspend fun update(user: UsersEntity): Int

    @Delete
    suspend fun delete(user: UsersEntity): Int
}
