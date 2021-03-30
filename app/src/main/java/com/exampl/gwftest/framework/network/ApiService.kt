package com.exampl.gwftest.framework.network

import com.exampl.gwftest.framework.network.request.LoginRequest
import com.exampl.gwftest.framework.network.response.MeasurementResponse
import com.exampl.gwftest.framework.network.response.TokenResponse
import com.exampl.gwftest.framework.network.response.TotalResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/token/")
    suspend fun login(
        @Body body: LoginRequest
    ): TokenResponse

    @GET("reports/measurements/")
    suspend fun getMeasurements(): List<MeasurementResponse>

    @GET("reports/measurements/total/")
    suspend fun getTotal(): TotalResponse

}