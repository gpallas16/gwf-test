package com.exampl.gwftest.framework.db.room.dao.measurement

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exampl.gwftest.framework.db.room.model.MeasurementModel

@Dao
interface RoomMeasurementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(measurementModels: List<MeasurementModel>)

    @Query("SELECT * FROM MeasurementModel")
    suspend fun getAll(): List<MeasurementModel>

    @Query("SELECT * FROM MeasurementModel WHERE meterId = :id")
    suspend fun getByMeterId(id: String): MeasurementModel

}