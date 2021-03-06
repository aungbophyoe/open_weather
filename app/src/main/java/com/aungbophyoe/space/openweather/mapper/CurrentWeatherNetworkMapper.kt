package com.aungbophyoe.space.openweather.mapper

import com.aungbophyoe.space.openweather.model.CurrentWeather
import com.aungbophyoe.space.openweather.network.response.CurrentWeatherNetworkEntity
import javax.inject.Inject

class CurrentWeatherNetworkMapper @Inject constructor() : EntityMapper<CurrentWeatherNetworkEntity, CurrentWeather> {
    override fun mapFromEntity(entity: CurrentWeatherNetworkEntity): CurrentWeather {
        return CurrentWeather(entity.id,entity.name,entity.weather,entity.detail,entity.dateTime,entity.wind)
    }

    override fun mapToEntity(domainModel: CurrentWeather): CurrentWeatherNetworkEntity {
        return CurrentWeatherNetworkEntity(domainModel.id,domainModel.name,domainModel.weather,domainModel.detail,domainModel.dateTime,domainModel.wind)
    }
}