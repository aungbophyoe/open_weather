package com.aungbophyoe.space.openweather.network.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("cod")
    val code : String,
    @SerializedName("message")
    val msg : String
) {
}