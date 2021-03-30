package com.exampl.gwftest.framework.db.room.dao.total

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exampl.gwftest.framework.db.room.model.TotalModel

@Dao
interface RoomTotalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(total: TotalModel)

    @Query("SELECT * FROM TotalModel")
    suspend fun get(): TotalModel


}