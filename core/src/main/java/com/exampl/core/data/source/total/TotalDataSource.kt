package com.exampl.core.data.source.total

import com.exampl.core.domain.entity.Total

interface TotalDataSource {

    suspend fun insertTotal(total: Total)

    suspend fun getTotal(): Total

}