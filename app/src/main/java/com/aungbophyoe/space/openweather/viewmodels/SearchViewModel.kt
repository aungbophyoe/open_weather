package com.aungbophyoe.space.openweather.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aungbophyoe.space.openweather.model.CurrentWeather
import com.aungbophyoe.space.openweather.repository.MainRepository
import com.aungbophyoe.space.openweather.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _currentWeather : MutableLiveData<CurrentWeather?> = MutableLiveData()
    val currentWeather : LiveData<CurrentWeather?> get() = _currentWeather
    private val _noData : MutableLiveData<String?> = MutableLiveData()
    val noData : LiveData<String?> get() = _noData

    fun getWeatherByCity(cityName:String){
        viewModelScope.launch {
            _loading.value = true
            repository.getCurrentWeatherByCity(cityName?:"")
                .onEach { dataState ->
                    when(dataState){
                        is DataState.Loading -> {
                            _loading.value = true
                            _noData.value = null
                            _currentWeather.value = null
                            Log.d("viewModel","loading")
                        }
                        is DataState.Success -> {
                            _loading.value = false
                            _currentWeather.value = dataState.data
                            _noData.value = null
                            Log.d("viewModel","success")
                        }
                        is DataState.NoData -> {
                            _loading.value = false
                            _currentWeather.value = null
                            _noData.value = dataState.msg
                        }
                        is DataState.Error -> {
                            _loading.value = false
                            _noData.value = null
                            _currentWeather.value = null
                            Log.d("viewModel","${dataState.exception}")
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}