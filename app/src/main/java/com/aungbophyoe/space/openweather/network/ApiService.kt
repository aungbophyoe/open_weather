package com.aungbophyoe.space.openweather.network

import com.aungbophyoe.space.openweather.BuildConfig
import com.aungbophyoe.space.openweather.network.response.CurrentWeatherNetworkEntity
import com.aungbophyoe.space.openweather.network.response.OpenWeatherNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather?")
    suspend fun getCurrentWeather(@Query("q")searchCity:String, @Query("units")unit:String, @Query("appid")token:String=BuildConfig.AUTH_TOKEN) : Response<CurrentWeatherNetworkEntity>

    @GET("onecall?")
    suspend fun getDailyWeatherLocation(@Query("lat")lat:String, @Query("lon")lon:String, @Query("units")unit:String, @Query("exclude")exclude:String, @Query("appid")token:String= BuildConfig.AUTH_TOKEN): Response<OpenWeatherNetworkEntity>

    @GET("weather?")
    suspend fun getCWWithLocation(@Query("lat")lat:String, @Query("lon")lon:String, @Query("units")unit:String, @Query("appid")token:String= BuildConfig.AUTH_TOKEN): Response<CurrentWeatherNetworkEntity>
}