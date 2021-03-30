package com.exampl.core.data.repository;

import com.exampl.core.data.source.total.TotalDataSource
import com.exampl.core.data.source.total.TotalRemoteDataSource
import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Total

class TotalRepository(
    private val dataSource: TotalDataSource,
    private val remoteDataSource: TotalRemoteDataSource
) {

    private suspend fun insertTotal(total: Total) = dataSource.insertTotal(total)

    suspend fun getTotal(): Total{
        return when(val result = remoteDataSource.getTotal()) {
            is Result.Success -> {
                insertTotal(result.data)
                result.data
            }
            is Result.Error -> dataSource.getTotal()
        }
    }
}