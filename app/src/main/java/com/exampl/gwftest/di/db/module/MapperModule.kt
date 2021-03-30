package com.exampl.gwftest.di.db.module

import com.exampl.gwftest.framework.db.room.mapper.MeasurementMapper
import com.exampl.gwftest.framework.db.room.mapper.TotalMapper
import com.exampl.gwftest.framework.network.mapper.RemoteMeasurementMapper
import com.exampl.gwftest.framework.network.mapper.RemoteTotalMapper
import dagger.Module
import dagger.Provides

@Module
object MapperModule {

    @Provides
    fun provideMeasurementMapper() = MeasurementMapper()

    @Provides
    fun provideRemoteMeasurementMapper() = RemoteMeasurementMapper()

    @Provides
    fun provideTotalMapper() = TotalMapper()

    @Provides
    fun provideRemoteTotalMapper() = RemoteTotalMapper()

}