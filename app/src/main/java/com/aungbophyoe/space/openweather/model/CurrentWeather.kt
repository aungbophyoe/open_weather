package com.aungbophyoe.space.openweather.model

import com.aungbophyoe.space.openweather.network.response.CurrentWeatherNetworkEntity

data class CurrentWeather(
    var id : Int,
    var name : String,
    var weather: List<CurrentWeatherNetworkEntity.Weather>,
    var detail: CurrentWeatherNetworkEntity.Detail
)