package com.exampl.gwftest.framework.db.room.dao.total

import com.exampl.core.data.source.total.TotalDataSource
import com.exampl.core.domain.entity.Total
import com.exampl.gwftest.framework.db.room.mapper.TotalMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomTotalDataSource @Inject constructor(
    private val roomTotalDao: RoomTotalDao,
    private val totalMapper: TotalMapper
) : TotalDataSource {

    override suspend fun insertTotal(total: Total) {
        withContext(Dispatchers.IO) {
            roomTotalDao.insert(totalMapper.toModel(total))
        }
    }

    override suspend fun getTotal(): Total {
        return withContext(Dispatchers.IO) {
            totalMapper.toEntity(roomTotalDao.get())
        }
    }

}