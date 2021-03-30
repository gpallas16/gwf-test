package com.exampl.gwftest.di.db.module

import android.app.Application
import com.exampl.core.data.source.measurement.MeasurementDataSource
import com.exampl.core.data.source.measurement.MeasurementRemoteDataSource
import com.exampl.core.data.source.token.TokenDataSource
import com.exampl.core.data.source.total.TotalDataSource
import com.exampl.core.data.source.total.TotalRemoteDataSource
import com.exampl.gwftest.framework.db.prefs.PrefsTokenDataSource
import com.exampl.gwftest.framework.db.remote.RetrofitMeasurementRemoteDataSource
import com.exampl.gwftest.framework.db.remote.RetrofitTotalRemoteDataSource
import com.exampl.gwftest.framework.db.room.dao.measurement.RoomMeasurementDao
import com.exampl.gwftest.framework.db.room.dao.measurement.RoomMeasurementDataSource
import com.exampl.gwftest.framework.db.room.dao.total.RoomTotalDao
import com.exampl.gwftest.framework.db.room.dao.total.RoomTotalDataSource
import com.exampl.gwftest.framework.db.room.mapper.MeasurementMapper
import com.exampl.gwftest.framework.db.room.mapper.TotalMapper
import com.exampl.gwftest.framework.network.ApiService
import com.exampl.gwftest.framework.network.mapper.RemoteMeasurementMapper
import com.exampl.gwftest.framework.network.mapper.RemoteTotalMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMeasurementDataSource(
        roomMeasurementDao: RoomMeasurementDao,
        measurementMapper: MeasurementMapper
    ): MeasurementDataSource = RoomMeasurementDataSource(roomMeasurementDao, measurementMapper)

    @Singleton
    @Provides
    fun provideRemoteMeasurementDataSource(
        apiService: ApiService,
        remoteMeasurementMapper: RemoteMeasurementMapper
    ): MeasurementRemoteDataSource =
        RetrofitMeasurementRemoteDataSource(apiService, remoteMeasurementMapper)

    @Singleton
    @Provides
    fun provideTotalDataSource(
        roomTotalDao: RoomTotalDao,
        totalMapper: TotalMapper
    ): TotalDataSource = RoomTotalDataSource(roomTotalDao, totalMapper)

    @Singleton
    @Provides
    fun provideTotalRemoteDataSource(
        apiService: ApiService,
        remoteTotalMapper: RemoteTotalMapper
    ): TotalRemoteDataSource = RetrofitTotalRemoteDataSource(apiService, remoteTotalMapper)

    @Singleton
    @Provides
    fun provideTokenDataSource(
        application: Application
    ): TokenDataSource = PrefsTokenDataSource(application)

}