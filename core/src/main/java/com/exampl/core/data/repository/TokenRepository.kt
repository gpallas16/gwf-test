package com.exampl.core.data.repository;

import com.exampl.core.data.source.token.TokenDataSource
import kotlin.math.log

open class TokenRepository(
    private val tokenDataSource: TokenDataSource
) {

    init {
        println("dfl")
    }

    open fun refreshToken(){}

    fun setAccessToken(accessToken: String?) {
        tokenDataSource.setAccessToken(accessToken)
        refreshToken()
    }

    fun setRefreshToken(refreshToken: String?) = tokenDataSource.setRefreshToken(refreshToken)

    fun getAccessToken(): String? = tokenDataSource.getAccessToken()

    fun getRefreshToken(): String? = tokenDataSource.getRefreshToken()

    fun clearAll() {
        setAccessToken(null)
        setRefreshToken(null)
    }
}
