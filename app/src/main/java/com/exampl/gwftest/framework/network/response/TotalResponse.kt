package com.exampl.gwftest.framework.network.response

data class TotalResponse(
    val meters:Int,
    val volume: Double,
    val error: Int,
    val readouts: Int
)