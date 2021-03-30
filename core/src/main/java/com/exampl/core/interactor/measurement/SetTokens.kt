package com.exampl.core.interactor.measurement

import com.exampl.core.data.repository.TokenRepository
import com.exampl.core.domain.base.Result
import com.exampl.core.interactor.UseCase

class SetTokens(
    private val tokenRepository: TokenRepository
) : UseCase<Unit, SetTokens.Token>() {

    override suspend fun run(params: Token): Result<Unit> {
        return try {
            tokenRepository.setAccessToken(params.access)
            tokenRepository.setRefreshToken(params.refresh)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    data class Token(
        val access: String,
        val refresh: String
    )

}