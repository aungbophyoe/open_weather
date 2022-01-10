package com.aungbophyoe.space.openweather.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.aungbophyoe.space.openweather.model.CurrentWeather

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