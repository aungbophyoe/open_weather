package com.aungbophyoe.space.openweather.mapper

interface EntityMapper<Entity,DomainModel> {
    fun mapFromEntity(entity: Entity) : DomainModel
    fun mapToEntity(domainModel: DomainModel) : Entity
}