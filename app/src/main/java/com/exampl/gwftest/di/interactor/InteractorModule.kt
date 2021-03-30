package com.exampl.gwftest.di.interactor

import com.exampl.core.data.repository.MeasurementRepository
import com.exampl.core.data.repository.TokenRepository
import com.exampl.core.data.repository.TotalRepository
import com.exampl.core.interactor.measurement.GetMeasurement
import com.exampl.core.interactor.measurement.GetMeasurements
import com.exampl.core.interactor.measurement.GetTotal
import com.exampl.core.interactor.measurement.SetTokens
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideGetMeasurements(measurementRepository: MeasurementRepository): GetMeasurements =
        GetMeasurements(measurementRepository)

    @Provides
    fun provideGetMeasurement(measurementRepository: MeasurementRepository): GetMeasurement =
        GetMeasurement(measurementRepository)

    @Provides
    fun provideGetTotal(totalRepository: TotalRepository): GetTotal =
        GetTotal(totalRepository)

    @Provides
    fun provideSetTokens(tokenRepository: TokenRepository): SetTokens =
        SetTokens(tokenRepository)

}