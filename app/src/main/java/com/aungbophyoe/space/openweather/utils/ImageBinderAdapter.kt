package com.aungbophyoe.space.openweather.utils

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.aungbophyoe.space.openweather.R

object ImageBinderAdapter {
    fun setImageUrl(imageView: ImageView, url: String) {
        if (url == null) {
            imageView.load(R.drawable.ic_cloud)
        } else {
            imageView.load(url){
                crossfade(true)
                placeholder(R.drawable.ic_cloud)
                transformations(CircleCropTransformation())
            }
        }
    }
}