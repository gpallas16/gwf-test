package com.exampl.gwftest.framework.db.room.mapper

import com.exampl.core.domain.base.EntityMapper
import com.exampl.core.domain.entity.Total
import com.exampl.gwftest.framework.db.room.model.TotalModel

class TotalMapper : EntityMapper<Total, TotalModel> {

    override fun toEntity(model: TotalModel): Total {
        return Total(
            model.meters,
            model.volume,
            model.error,
            model.readouts
        )
    }

    override fun toModel(entity: Total): TotalModel {
        return TotalModel(
            entity.meters,
            entity.volume,
            entity.error,
            entity.readouts
        )
    }
}