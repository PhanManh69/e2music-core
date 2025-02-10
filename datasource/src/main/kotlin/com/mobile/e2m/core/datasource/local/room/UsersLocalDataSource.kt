package com.mobile.e2m.core.datasource.local.room

import com.mobile.e2m.core.datasource.local.room.entity.UsersEntity

interface UsersLocalDataSource {
    suspend fun getUsers(): List<UsersEntity>

    suspend fun getUsername(): List<String>

    suspend fun getEmail(): List<String>

    suspend fun getUsersByEmailOrUsername(emailAccount: String): List<UsersEntity>

    suspend fun updatePassword(id: Int, newPassword: String): Int

    suspend fun insertUsers(user: UsersEntity): Long

    suspend fun updateUsers(user: UsersEntity): Int

    suspend fun deleteUsers(user: UsersEntity): Int
}
