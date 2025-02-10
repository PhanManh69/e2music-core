package com.mobile.e2m.core.datasource.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobile.e2m.core.datasource.local.room.dao.UsersDao
import com.mobile.e2m.core.datasource.local.room.entity.UsersEntity

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}
