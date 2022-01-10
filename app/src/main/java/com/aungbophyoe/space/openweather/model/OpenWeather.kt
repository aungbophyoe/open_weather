package com.aungbophyoe.space.openweather.model

import com.aungbophyoe.space.openweather.network.response.OpenWeatherNetworkEntity

data class OpenWeather(
    var lat : Double,
    var lon : Double,
    var timezone : String,
    var current : OpenWeatherNetworkEntity.Current,
    var daily : List<OpenWeatherNetworkEntity.Daily>
) {
}