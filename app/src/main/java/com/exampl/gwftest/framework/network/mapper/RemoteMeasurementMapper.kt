package com.exampl.gwftest.framework.network.mapper

import com.exampl.core.domain.base.EntityMapper
import com.exampl.core.domain.entity.Measurement
import com.exampl.core.domain.entity.State
import com.exampl.gwftest.framework.network.response.MeasurementResponse

class RemoteMeasurementMapper : EntityMapper<Measurement, MeasurementResponse> {

    override fun toEntity(model: MeasurementResponse): Measurement {
        return Measurement(
            model.meter_id,
            model.lat,
            model.lng,
            model.mp_name,
            model.comm_mod_type,
            model.comm_mod_serial,
            model.last_entry,
            model.volume,
            model.battery_lifetime,
            State(
                model.state.encoder_error,
                model.state.us_water_level,
                model.state.v_sensor_comm_timout,
                model.state.water_level_error,
                model.state.t_air_error,
                model.state.t_water_error,
                model.state.w_air_error,
                model.state.w_water_error,
                model.state.velocity_error,
                model.state.system_error
            )
        )
    }

    override fun toModel(entity: Measurement): MeasurementResponse {
        return MeasurementResponse(
            entity.meterId,
            entity.lat,
            entity.lng,
            entity.mpName,
            entity.commModType,
            entity.commModSerial,
            entity.lastEntry,
            entity.volume,
            entity.batteryLifetime,
            com.exampl.gwftest.framework.network.response.State(
                entity.state.encoderError,
                entity.state.usWaterLevel,
                entity.state.vSensorCommTimOut,
                entity.state.waterLevelError,
                entity.state.tAirError,
                entity.state.tWaterError,
                entity.state.wAirError,
                entity.state.wWaterError,
                entity.state.velocityError,
                entity.state.systemError
            )
        )
    }

}