package com.exampl.core.data.source.token

interface TokenDataSource {

    fun setAccessToken(accessToken: String?)

    fun setRefreshToken(refreshToken: String?)

    fun getAccessToken(): String?

    fun getRefreshToken(): String?

}