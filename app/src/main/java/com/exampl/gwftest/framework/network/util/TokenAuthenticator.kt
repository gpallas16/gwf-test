package com.exampl.gwftest.framework.network.util

import android.util.Log
import com.exampl.core.data.repository.TokenRepository
import com.exampl.gwftest.framework.network.RefreshTokenService
import com.exampl.gwftest.framework.network.request.RefreshTokenRequest
import com.exampl.gwftest.framework.network.response.RefreshTokenResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val refreshTokenService: RefreshTokenService,
    private val tokenRepository: TokenRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            try {
                val refreshTokenResponse = getUpdatedToken()
                tokenRepository.setAccessToken(refreshTokenResponse.access)
                Log.d(
                    this.javaClass.simpleName,
                    "authenticate: Refresh token arrived: ${refreshTokenResponse.access}"
                )
                response.request().newBuilder()
                    .header("Authorization", "Bearer ${refreshTokenResponse.access}")
                    .build()
            } catch (t: Throwable) {
                tokenRepository.setAccessToken(null)
                tokenRepository.setRefreshToken(null)
                Log.d(this.javaClass.simpleName, "authenticate: Refresh token failed")
                null
            }
        }
    }

    private suspend fun getUpdatedToken(): RefreshTokenResponse {
        return refreshTokenService.refreshToken(
            RefreshTokenRequest(
                tokenRepository.getRefreshToken() ?: ""
            )
        )
    }
}
