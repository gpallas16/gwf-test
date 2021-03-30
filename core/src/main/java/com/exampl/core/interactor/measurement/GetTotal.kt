package com.exampl.core.interactor.measurement

import com.exampl.core.data.repository.TotalRepository
import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Total
import com.exampl.core.interactor.UseCase

class GetTotal(
    private val totalRepository: TotalRepository
) : UseCase<Total, Unit>() {

    override suspend fun run(params: Unit): Result<Total> {
        return try {
            Result.Success(totalRepository.getTotal())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}