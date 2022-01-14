package com.aungbophyoe.space.openweather.repository

import android.util.Log
import com.aungbophyoe.space.openweather.mapper.CurrentWeatherNetworkMapper
import com.aungbophyoe.space.openweather.mapper.NetworkMapper
import com.aungbophyoe.space.openweather.model.CurrentWeather
import com.aungbophyoe.space.openweather.model.OpenWeather
import com.aungbophyoe.space.openweather.network.ApiService
import com.aungbophyoe.space.openweather.network.response.ErrorResponse
import com.aungbophyoe.space.openweather.utils.DataState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import org.json.JSONObject




class MainRepository @Inject constructor(private val apiService: ApiService, private val networkMapper: NetworkMapper,private val currentWeatherNetworkMapper: CurrentWeatherNetworkMapper) {
    suspend fun getCurrentWeatherByCity(searchCity:String) : Flow<DataState<CurrentWeather>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getCurrentWeather(searchCity = searchCity,unit = "metric")
            if(response.isSuccessful){
                val data = response.body()!!
                emit(DataState.Success(currentWeatherNetworkMapper.mapFromEntity(data)))
            }else{
                val jObjError = JSONObject(response.errorBody()?.string())
                Log.d("viewModel","${jObjError.getString("message")}")
                emit(DataState.NoData(jObjError.getString("message")))
            }
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getDailyWeatherLocation(lat:String, lon:String) : Flow<DataState<OpenWeather>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getDailyWeatherLocation(lat = lat,lon = lon,exclude = "hourly,minutely",unit = "metric")
            if(response.isSuccessful){
                val data = response.body()!!
                emit(DataState.Success(networkMapper.mapFromEntity(data)))
            }
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getCurrentWeatherLocation(lat:String, lon:String) : Flow<DataState<CurrentWeather>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getCWWithLocation(lat = lat,lon = lon,unit = "metric")
            if(response.isSuccessful){
                val data = response.body()!!
                emit(DataState.Success(currentWeatherNetworkMapper.mapFromEntity(data)))
            }
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}