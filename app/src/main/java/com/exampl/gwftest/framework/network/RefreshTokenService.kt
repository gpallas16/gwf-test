package com.exampl.gwftest.framework.network

import com.exampl.gwftest.framework.network.request.RefreshTokenRequest
import com.exampl.gwftest.framework.network.response.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenService {

    @POST("refresh/")
    suspend fun refreshToken(
        @Body body: RefreshTokenRequest
    ): RefreshTokenResponse

}