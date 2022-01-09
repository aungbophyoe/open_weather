package com.aungbophyoe.space.openweather.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.aungbophyoe.space.openweather.model.CurrentWeather

@BindingAdapter("textTitle")
fun TextView.setTitle(item : CurrentWeather){
    text = item.weather.getOrNull(0)?.status
}

@BindingAdapter("image")
fun ImageView.setImage(item: CurrentWeather){
    ImageBinderAdapter.setImageUrl(this,"https://openweathermap.org/img/wn/${item.weather.getOrNull(0)?.icon ?: "02d"}@4x.png")
}