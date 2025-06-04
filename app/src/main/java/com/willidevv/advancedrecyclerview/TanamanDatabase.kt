package com.willidevv.advancedrecyclerview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tanaman::class], version = 1)
abstract class TanamanDatabase : RoomDatabase() {

    abstract fun tanamanDao(): TanamanDao

    companion object {
        private var INSTANCE: TanamanDatabase? = null

        fun getInstance(context: Context): TanamanDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TanamanDatabase::class.java,"tanaman_database"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}