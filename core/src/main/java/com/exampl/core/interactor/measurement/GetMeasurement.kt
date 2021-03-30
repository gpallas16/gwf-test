package com.exampl.core.interactor.measurement

import com.exampl.core.data.repository.MeasurementRepository
import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Measurement
import com.exampl.core.interactor.UseCase

class GetMeasurement(private val measurementRepository: MeasurementRepository) : UseCase<Measurement, String>() {

    override suspend fun run(params: String): Result<Measurement> {
        return try {
            Result.Success(measurementRepository.getMeasurementByMeterId(params))
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

}