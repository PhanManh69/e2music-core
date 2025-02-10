package com.mobile.e2m.core.datasource.local.room.dao

import com.mobile.e2m.core.datasource.local.room.entity.UsersEntity

class FakeUsersDao : UsersDao {
    private val users = mutableListOf<UsersEntity>()

    override suspend fun getUsers(): List<UsersEntity> {
        return users
    }

    override suspend fun getUsername(): List<String> {
        return users.map { it.username }
    }

    override suspend fun getEmail(): List<String> {
        return users.map { it.email }
    }

    override suspend fun getUserByEmailOrUsername(
        emailAccount: String,
    ): List<UsersEntity> {
        return users
    }

    override suspend fun updatePassword(id: Int, newPassword: String): Int {
        val index = users.indexOfFirst { it.id == id }
        return if (index != -1) {
            users[index] = users[index].copy(password = newPassword)
            1
        } else {
            0
        }
    }

    override suspend fun insert(user: UsersEntity): Long {
        users.add(user)
        return user.id.toLong()
    }

    override suspend fun update(user: UsersEntity): Int {
        val index = users.indexOfFirst { it.id == user.id }
        return if (index != -1) {
            users[index] = user
            1
        } else 0
    }

    override suspend fun delete(user: UsersEntity): Int {
        return if (users.remove(user)) 1 else 0
    }
}
