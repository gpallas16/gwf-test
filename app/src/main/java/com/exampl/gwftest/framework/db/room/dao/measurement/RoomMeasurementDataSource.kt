package com.exampl.gwftest.framework.db.room.dao.measurement

import com.exampl.core.data.source.measurement.MeasurementDataSource
import com.exampl.core.domain.entity.Measurement
import com.exampl.gwftest.framework.db.room.mapper.MeasurementMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomMeasurementDataSource @Inject constructor(
    private val roomMeasurementDao: RoomMeasurementDao,
    private val measurementMapper: MeasurementMapper
) : MeasurementDataSource {

    override suspend fun insertMeasurements(measurements: List<Measurement>) {
        withContext(Dispatchers.IO) {
            roomMeasurementDao.insertAll(measurements.map { measurementMapper.toModel(it) })
        }
    }

    override suspend fun getMeasurements(): List<Measurement> {
        return withContext(Dispatchers.IO) {
            roomMeasurementDao.getAll().map { measurementMapper.toEntity(it) }
        }
    }

    override suspend fun getMeasurementByMeterId(meterId: String): Measurement {
        return withContext(Dispatchers.IO) {
            measurementMapper.toEntity(roomMeasurementDao.getByMeterId(meterId))
        }
    }

}