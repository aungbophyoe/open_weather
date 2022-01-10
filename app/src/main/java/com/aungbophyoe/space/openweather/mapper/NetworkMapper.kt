package com.aungbophyoe.space.openweather.mapper

import com.aungbophyoe.space.openweather.model.CurrentWeather
import com.aungbophyoe.space.openweather.model.OpenWeather
import com.aungbophyoe.space.openweather.network.response.CurrentWeatherNetworkEntity
import com.aungbophyoe.space.openweather.network.response.OpenWeatherNetworkEntity
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<OpenWeatherNetworkEntity, OpenWeather> {
    override fun mapFromEntity(entity: OpenWeatherNetworkEntity): OpenWeather {
        return OpenWeather(entity.lat,entity.lon,entity.timezone,entity.current,entity.daily)
    }

    override fun mapToEntity(domainModel: OpenWeather): OpenWeatherNetworkEntity {
        return OpenWeatherNetworkEntity(domainModel.lat,domainModel.lon,domainModel.timezone,domainModel.current,domainModel.daily)
    }
}