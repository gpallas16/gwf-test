package com.exampl.core.interactor.measurement

import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Measurement
import com.exampl.core.interactor.UseCase
import com.exampl.core.data.repository.MeasurementRepository

class GetMeasurements(
    private val measurementRepository: MeasurementRepository
) : UseCase<List<Measurement>, Unit>() {

    override suspend fun run(params: Unit): Result<List<Measurement>> {
        return try {
            Result.Success(measurementRepository.getMeasurements())
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

}