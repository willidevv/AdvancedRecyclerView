package com.willidevv.advancedrecyclerview

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tanaman(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaTanaman: String,
    val deskripsiTanaman: String,
    val category: String
)
