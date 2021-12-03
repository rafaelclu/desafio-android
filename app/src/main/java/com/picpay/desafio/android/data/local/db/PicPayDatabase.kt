package com.picpay.desafio.android.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.db.user.UserDao
import com.picpay.desafio.android.data.local.db.user.entity.UserEntity

const val DATABASE_VERSION = 1

@Database(
    entities = [UserEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class PicPayDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: PicPayDatabase? = null

        fun createDatabase(context: Context): PicPayDatabase =
            Room.databaseBuilder(context, PicPayDatabase::class.java, "picpay.db")
                .build()

        fun getDatabase(context: Context): PicPayDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = createDatabase(context)
                INSTANCE = instance

                instance
            }
        }
    }

}