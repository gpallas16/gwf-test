package com.exampl.gwftest.framework.db.room.mapper

import com.exampl.core.domain.base.EntityMapper
import com.exampl.core.domain.entity.Measurement
import com.exampl.gwftest.framework.db.room.model.MeasurementModel

class MeasurementMapper : EntityMapper<Measurement, MeasurementModel> {

    override fun toEntity(model: MeasurementModel): Measurement {
        return Measurement(
            model.meterId,
            model.lat,
            model.lng,
            model.mpName,
            model.commModType,
            model.commModSerial,
            model.lastEntry,
            model.volume,
            model.batteryLifetime,
            model.state
        )
    }

    override fun toModel(entity: Measurement): MeasurementModel {
        return MeasurementModel(
            entity.meterId,
            entity.lat,
            entity.lng,
            entity.mpName,
            entity.commModType,
            entity.commModSerial,
            entity.lastEntry,
            entity.volume,
            entity.batteryLifetime,
            entity.state
        )
    }

}