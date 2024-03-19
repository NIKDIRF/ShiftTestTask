package com.app.shift.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [UserEntity::class], version = 1)
abstract class UserDb : RoomDatabase() {
    abstract fun getDao(): UserDao

    companion object {
        fun getDb(context: Context): UserDb{
            return Room.databaseBuilder(
                context.applicationContext,
                UserDb::class.java,
                "user.db"
            ).build()
        }
    }
}