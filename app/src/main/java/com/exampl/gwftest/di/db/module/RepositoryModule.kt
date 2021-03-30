package com.exampl.gwftest.di.db.module

import com.exampl.core.data.repository.MeasurementRepository
import com.exampl.core.data.repository.TokenRepository
import com.exampl.core.data.repository.TotalRepository
import com.exampl.core.data.source.measurement.MeasurementDataSource
import com.exampl.core.data.source.measurement.MeasurementRemoteDataSource
import com.exampl.core.data.source.token.TokenDataSource
import com.exampl.core.data.source.total.TotalDataSource
import com.exampl.core.data.source.total.TotalRemoteDataSource
import com.exampl.gwftest.framework.db.prefs.TokenRepositoryExt
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMeasurementRepository(
        measurementDataSource: MeasurementDataSource,
        remoteDataSource: MeasurementRemoteDataSource
    ) = MeasurementRepository(remoteDataSource, measurementDataSource)

    @Singleton
    @Provides
    fun provideTotalRepository(
        totalDataSource: TotalDataSource,
        totalRemoteDataSource: TotalRemoteDataSource
    ) = TotalRepository(totalDataSource, totalRemoteDataSource)

    @Singleton
    @Provides
    fun provideTokenRepositoryExt(tokenDataSource: TokenDataSource): TokenRepository =
        TokenRepositoryExt(tokenDataSource)

}