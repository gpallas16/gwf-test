package com.exampl.gwftest.framework.db.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TotalModel(
    val meters:Int,
    val volume: Double,
    val error: Int,
    val readouts: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)