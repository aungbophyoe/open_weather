package com.aungbophyoe.space.openweather.network

import com.aungbophyoe.space.openweather.BuildConfig
import com.aungbophyoe.space.openweather.network.response.CurrentWeatherNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather?")
    suspend fun getCurrentWeather(@Query("q")searchCity:String, @Query("units")unit:String, @Query("appid")token:String=BuildConfig.AUTH_TOKEN) : Response<CurrentWeatherNetworkEntity>

    @GET("weather?")
    suspend fun getCWLocation(@Query("lat")lat:String, @Query("lon")lon:String, @Query("units")unit:String, @Query("appid")token:String= BuildConfig.AUTH_TOKEN): Response<CurrentWeatherNetworkEntity>
}