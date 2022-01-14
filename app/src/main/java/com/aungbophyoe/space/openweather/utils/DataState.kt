package com.aungbophyoe.space.openweather.utils

sealed class DataState<out R> {
    data class Success<out T>(val data:T):DataState<T>()
    data class Error(val exception:Exception):DataState<Nothing>()
    data class NoData(val msg:String):DataState<Nothing>()
    object Loading: DataState<Nothing>()
}
