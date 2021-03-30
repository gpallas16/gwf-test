package com.exampl.gwftest.framework.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exampl.gwftest.framework.db.room.converter.RoomConverter
import com.exampl.gwftest.framework.db.room.dao.measurement.RoomMeasurementDao
import com.exampl.gwftest.framework.db.room.dao.total.RoomTotalDao
import com.exampl.gwftest.framework.db.room.model.MeasurementModel
import com.exampl.gwftest.framework.db.room.model.TotalModel

@Database(
    entities = [MeasurementModel::class, TotalModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverter::class)
abstract class IRoomDatabase : RoomDatabase() {

    abstract fun measurementDao(): RoomMeasurementDao

    abstract fun totalDao() : RoomTotalDao
}