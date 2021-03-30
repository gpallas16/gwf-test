package com.exampl.core.domain.entity

data class Measurement(
    val meterId: String,
    val lat: Double,
    val lng: Double,
    val mpName: String,
    val commModType: String,
    val commModSerial: String,
    val lastEntry: String?,
    val volume: Double,
    val batteryLifetime: Int?,
    val state: State
)

data class State(
    val encoderError: Boolean?,
    val usWaterLevel: Boolean?,
    val vSensorCommTimOut: Boolean?,
    val waterLevelError:Boolean?,
    val tAirError: Boolean?,
    val tWaterError: Boolean?,
    val wAirError: Boolean?,
    val wWaterError: Boolean?,
    val velocityError: Boolean?,
    val systemError: Boolean?
)