package com.exampl.gwftest.framework.db.room.converter

import androidx.room.TypeConverter
import com.exampl.core.domain.entity.State
import com.google.gson.Gson

class RoomConverter(private val gson: Gson = Gson()) {

    @TypeConverter
    fun jsonToState(json: String): State {
        return gson.fromJson(json, State::class.java)
    }

    @TypeConverter
    fun stateToJson(state: State): String {
        return gson.toJson(state)
    }

}