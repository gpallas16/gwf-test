package com.exampl.gwftest.framework.network.mapper

import com.exampl.core.domain.base.EntityMapper
import com.exampl.core.domain.entity.Total
import com.exampl.gwftest.framework.network.response.TotalResponse

class RemoteTotalMapper : EntityMapper<Total, TotalResponse> {

    override fun toEntity(model: TotalResponse): Total {
        return Total(
            model.meters,
            model.volume,
            model.error,
            model.readouts
        )
    }

    override fun toModel(entity: Total): TotalResponse {
        return TotalResponse(
            entity.meters,
            entity.volume,
            entity.error,
            entity.readouts
        )
    }
}