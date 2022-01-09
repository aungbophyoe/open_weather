package com.aungbophyoe.space.openweather.utils

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.aungbophyoe.space.openweather.R

object ImageBinderAdapter {
    fun setImageUrl(imageView: ImageView, url: String) {
        if (url == null) {
            imageView.load(R.mipmap.ic_launcher)
        } else {
            imageView.load(url){
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
                transformations(CircleCropTransformation())
            }
        }
    }
}