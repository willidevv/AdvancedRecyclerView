package com.willidevv.advancedrecyclerview

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TanamanDao {
    @Query("SELECT * FROM Tanaman")
    suspend fun getAll(): List<Tanaman>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tanaman: Tanaman)

    @Query("SELECT * FROM Tanaman WHERE id = :id LIMIT 1")
    suspend fun getById(id : Int): Tanaman?

}