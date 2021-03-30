package com.exampl.gwftest.framework.network.response

data class MeasurementResponse(
    val meter_id: String,
    val lat: Double,
    val lng: Double,
    val mp_name: String,
    val comm_mod_type: String,
    val comm_mod_serial: String,
    val last_entry: String?,
    val volume: Double,
    val battery_lifetime: Int?,
    val state: State
)

data class State(
    val encoder_error: Boolean?,
    val us_water_level: Boolean?,
    val v_sensor_comm_timout: Boolean?,
    val water_level_error:Boolean?,
    val t_air_error: Boolean?,
    val t_water_error: Boolean?,
    val w_air_error: Boolean?,
    val w_water_error: Boolean?,
    val velocity_error: Boolean?,
    val system_error: Boolean?
)