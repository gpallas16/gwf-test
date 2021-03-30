package com.exampl.core.data.source.measurement

import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Measurement

interface MeasurementRemoteDataSource {

    suspend fun getMeasurements() : Result<List<Measurement>>

}