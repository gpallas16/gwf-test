package com.exampl.gwftest.framework.db.remote

import com.exampl.core.data.source.measurement.MeasurementRemoteDataSource
import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Measurement
import com.exampl.gwftest.framework.network.ApiService
import com.exampl.gwftest.framework.network.mapper.RemoteMeasurementMapper

class RetrofitMeasurementRemoteDataSource(
    private val apiService: ApiService,
    private val remoteMeasurementMapper: RemoteMeasurementMapper,
) : MeasurementRemoteDataSource {

    override suspend fun getMeasurements(): Result<List<Measurement>> {
        return try {
            Result.Success(
                apiService.getMeasurements().map { remoteMeasurementMapper.toEntity(it) }
            )
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }


}