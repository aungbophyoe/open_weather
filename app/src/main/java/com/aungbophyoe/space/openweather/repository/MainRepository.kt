package com.aungbophyoe.space.openweather.repository

import com.aungbophyoe.space.openweather.mapper.NetworkMapper
import com.aungbophyoe.space.openweather.model.CurrentWeather
import com.aungbophyoe.space.openweather.network.ApiService
import com.aungbophyoe.space.openweather.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService, private val networkMapper: NetworkMapper) {
    suspend fun getCurrentWeather(searchCity:String) : Flow<DataState<CurrentWeather>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getCurrentWeather(searchCity = searchCity,unit = "metric")
            if(response.isSuccessful){
                val data = response.body()!!
                emit(DataState.Success(networkMapper.mapFromEntity(data)))
            }
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getCWLocation(lat:String,lon:String) : Flow<DataState<CurrentWeather>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getCWLocation(lat = lat,lon = lon,unit = "metric")
            if(response.isSuccessful){
                val data = response.body()!!
                emit(DataState.Success(networkMapper.mapFromEntity(data)))
            }
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}