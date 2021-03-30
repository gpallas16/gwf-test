package com.exampl.gwftest.framework.db.remote

import com.exampl.core.data.source.total.TotalRemoteDataSource
import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Total
import com.exampl.gwftest.framework.network.ApiService
import com.exampl.gwftest.framework.network.mapper.RemoteTotalMapper

class RetrofitTotalRemoteDataSource(
    private val apiService: ApiService,
    private val remoteTotalMapper: RemoteTotalMapper
) : TotalRemoteDataSource {

    override suspend fun getTotal(): Result<Total> {
        return try {
            Result.Success(remoteTotalMapper.toEntity(apiService.getTotal()))
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }
}