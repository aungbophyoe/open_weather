package com.aungbophyoe.space.openweather.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aungbophyoe.space.openweather.model.CurrentWeather
import com.aungbophyoe.space.openweather.model.OpenWeather
import com.aungbophyoe.space.openweather.repository.MainRepository
import com.aungbophyoe.space.openweather.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _currentWeather : MutableLiveData<CurrentWeather?> = MutableLiveData()
    val currentWeather : LiveData<CurrentWeather?> get() = _currentWeather

    private val _dailyWeather : MutableLiveData<OpenWeather?> = MutableLiveData()
    val dailyWeather : LiveData<OpenWeather?> get() = _dailyWeather

    private val latitude : MutableLiveData<String> = MutableLiveData("16.8409")
    private val longitude : MutableLiveData<String> = MutableLiveData("96.1735")
    fun setLocation(_latitude:String,_longitude:String){
        latitude.value = _latitude
        longitude.value = _longitude
    }

    fun getWeather(){
        viewModelScope.launch {
            _loading.value = true
            repository.getCurrentWeatherLocation(lat = latitude.value ?: "16.8409",lon = longitude.value ?: "96.1735")
                .onEach { dataState ->
                    when(dataState){
                        is DataState.Loading -> {
                            _loading.value = true
                        }
                        is DataState.Success -> {
                            _loading.value = false
                            _currentWeather.value = dataState.data
                        }
                        is DataState.Error -> {
                            _loading.value = false
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    init {
        viewModelScope.launch {
            _loading.value = true
            repository.getDailyWeatherLocation(lat = latitude.value ?: "16.8409",lon = longitude.value ?: "96.1735")
                .onEach { dataState ->
                    when(dataState){
                        is DataState.Loading -> {
                            _loading.value = true
                        }
                        is DataState.Success -> {
                            _loading.value = false
                            Log.d("viewModel","success")
                            _dailyWeather.value = dataState.data
                        }
                        is DataState.Error -> {
                            _loading.value = false
                            Log.d("viewModel","${dataState.exception}")
                        }
                    }
                }
                .launchIn(viewModelScope)

            repository.getCurrentWeatherLocation(lat = latitude.value ?: "16.8409",lon = longitude.value ?: "96.1735")
                .onEach { dataState ->
                    when(dataState){
                        is DataState.Loading -> {
                            _loading.value = true
                        }
                        is DataState.Success -> {
                            _loading.value = false
                            Log.d("viewModel","success")
                            _currentWeather.value = dataState.data
                        }
                        is DataState.Error -> {
                            _loading.value = false
                            Log.d("viewModel","${dataState.exception}")
                        }
                    }
                }
                .launchIn(viewModelScope)
        }


    }
}