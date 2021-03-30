package com.exampl.gwftest.di.db.module

import android.app.Application
import androidx.room.Room
import com.exampl.gwftest.framework.db.room.IRoomDatabase
import com.exampl.gwftest.framework.db.room.dao.measurement.RoomMeasurementDao
import com.exampl.gwftest.framework.db.room.dao.total.RoomTotalDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): IRoomDatabase {
        return Room.databaseBuilder(application, IRoomDatabase::class.java, "gwf.db").build()
    }

    @Singleton
    @Provides
    fun provideRoomMeasurementDao(roomDatabase: IRoomDatabase): RoomMeasurementDao =
        roomDatabase.measurementDao()

    @Singleton
    @Provides
    fun provideRoomTotalDao(roomDatabase: IRoomDatabase): RoomTotalDao = roomDatabase.totalDao()


}