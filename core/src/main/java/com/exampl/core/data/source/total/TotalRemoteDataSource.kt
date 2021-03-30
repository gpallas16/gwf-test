package com.exampl.core.data.source.total

import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Total

interface TotalRemoteDataSource {

    suspend fun getTotal(): Result<Total>

}