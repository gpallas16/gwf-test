package com.exampl.gwftest.framework.db.room.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.exampl.core.domain.entity.State

@Entity(indices = [Index(value = ["meterId"])])
data class MeasurementModel(
    val meterId: String,
    val lat: Double,
    val lng: Double,
    val mpName: String,
    val commModType: String,
    val commModSerial: String,
    val lastEntry: String?,
    val volume: Double,
    val batteryLifetime: Int?,
    val state: State,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
