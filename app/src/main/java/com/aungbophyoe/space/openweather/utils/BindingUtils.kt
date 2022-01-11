package com.aungbophyoe.space.openweather.utils

import android.annotation.SuppressLint
import android.graphics.Path
import android.media.Image
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.openweather.adapter.DailyWeatherRecyclerAdapter
import com.aungbophyoe.space.openweather.model.CurrentWeather
import com.aungbophyoe.space.openweather.model.OpenWeather
import com.aungbophyoe.space.openweather.network.response.OpenWeatherNetworkEntity

@BindingAdapter("currentWeatherStatus")
fun TextView.setCurrentWeatherStatus(item : CurrentWeather?){
    text = "${item?.detail?.temp} °C"
}

@BindingAdapter("timeZoneName")
fun TextView.setTimeZoneName(item : CurrentWeather?){
    text = item?.name
}

@BindingAdapter("todayDate")
fun TextView.setTodayDate(item : CurrentWeather?){
    val date = Utility.getDateTime(item?.dateTime.toString())
    text = date ?: "2.2.2022"
}

@BindingAdapter("wind")
fun TextView.setCWWind(item : CurrentWeather?){
    text = item?.wind?.speed.toString()
}

@BindingAdapter("pressure")
fun TextView.setCWPressure(item : CurrentWeather?){
    text = item?.detail?.pressure.toString()
}

@BindingAdapter("humidity")
fun TextView.setCWHumidity(item : CurrentWeather?){
    text = item?.detail?.humidity.toString()
}

@BindingAdapter("feels_like")
fun TextView.setCWFeelsLike(item : CurrentWeather?){
    text = item?.detail?.feelsLike.toString()
}

@BindingAdapter("image")
fun ImageView.setImage(item: CurrentWeather?){
    ImageBinderAdapter.setImageUrl(this,"https://openweathermap.org/img/wn/${item?.weather?.getOrNull(0)?.icon ?: "02d"}@4x.png")
}

@BindingAdapter("dailyDate")
fun TextView.setDailyDate(item : OpenWeatherNetworkEntity.Daily?){
    val date = Utility.getDay(item?.dateTime.toString())
    text = date ?: ""
}

@BindingAdapter("dailyStatus")
fun TextView.setDailyStatus(item: OpenWeatherNetworkEntity.Daily?){
    text = "${item?.temp?.min} °C - ${item?.temp?.max} °C"
}

@BindingAdapter("dailyImage")
fun ImageView.setDailyImage(item : OpenWeatherNetworkEntity.Daily?){
    ImageBinderAdapter.setImageUrl(this,"https://openweathermap.org/img/wn/${item?.weather?.getOrNull(0)?.icon ?: "02d"}@4x.png")
}

@BindingAdapter("items")
fun setItems(listView : RecyclerView, items: OpenWeather?){
    val list = items?.daily
    (listView.adapter as DailyWeatherRecyclerAdapter).submitList(list)
}