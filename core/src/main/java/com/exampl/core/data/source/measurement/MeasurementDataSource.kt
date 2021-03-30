package com.exampl.core.data.source.measurement

import com.exampl.core.domain.entity.Measurement

interface MeasurementDataSource {

    suspend fun insertMeasurements(measurements: List<Measurement>)

    suspend fun getMeasurements(): List<Measurement>

    suspend fun getMeasurementByMeterId(meterId: String): Measurement

}