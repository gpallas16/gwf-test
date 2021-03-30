package com.exampl.core.data.repository

import com.exampl.core.data.source.measurement.MeasurementDataSource
import com.exampl.core.data.source.measurement.MeasurementRemoteDataSource
import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Measurement


class MeasurementRepository(
    private val remoteDataSource: MeasurementRemoteDataSource,
    private val dataSource: MeasurementDataSource
) {

    private suspend fun insertMeasurements(measurements: List<Measurement>) =
        dataSource.insertMeasurements(measurements)

    suspend fun getMeasurements(): List<Measurement> {
        return when (val result = remoteDataSource.getMeasurements()) {
            is Result.Success -> {
                insertMeasurements(result.data)
                result.data
            }
            is Result.Error -> dataSource.getMeasurements()
        }
    }

    suspend fun getMeasurementByMeterId(meterId: String): Measurement =
        dataSource.getMeasurementByMeterId(meterId)

}