package com.exampl.core.domain.base

interface EntityMapper<E, M> {

    fun toEntity(model: M): E

    fun toModel(entity: E): M

}