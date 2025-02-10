package com.mobile.e2m.core.datasource.local.room

import com.mobile.e2m.core.datasource.local.room.dao.UsersDao
import com.mobile.e2m.core.datasource.local.room.entity.UsersEntity

class UsersLocalDataSourceImpl(
    private val usersDao: UsersDao
) : UsersLocalDataSource {
    override suspend fun getUsers(): List<UsersEntity> = usersDao.getUsers()

    override suspend fun getUsername(): List<String> = usersDao.getUsername()

    override suspend fun getEmail(): List<String> = usersDao.getEmail()

    override suspend fun getUsersByEmailOrUsername(
        emailAccount: String,
    ): List<UsersEntity> = usersDao.getUserByEmailOrUsername(emailAccount)

    override suspend fun updatePassword(id: Int, newPassword: String): Int =
        usersDao.updatePassword(id = id, newPassword = newPassword)

    override suspend fun insertUsers(user: UsersEntity): Long = usersDao.insert(user)

    override suspend fun updateUsers(user: UsersEntity): Int = usersDao.update(user)

    override suspend fun deleteUsers(user: UsersEntity): Int = usersDao.delete(user)
}
