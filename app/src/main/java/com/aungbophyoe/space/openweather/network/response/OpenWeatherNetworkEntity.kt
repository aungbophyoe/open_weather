package com.aungbophyoe.space.openweather.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OpenWeatherNetworkEntity(
    @SerializedName("lat")
    @Expose
    var lat : Double,
    @SerializedName("lon")
    @Expose
    var lon : Double,
    @SerializedName("timezone")
    @Expose
    var timezone : String,
    @SerializedName("current")
    @Expose
    var current : Current,
    @SerializedName("daily")
    @Expose
    var daily : List<Daily>
) {
    data class Current(
        @SerializedName("dt")
        @Expose
        var dateTime : Long,
        @SerializedName("temp")
        @Expose
        var temp : Double,
        @SerializedName("feels_like")
        @Expose
        var feelsLike : Double,
        @SerializedName("pressure")
        @Expose
        var pressure : Int,
        @SerializedName("humidity")
        @Expose
        var humidity : Int,
        @SerializedName("wind_speed")
        @Expose
        var windSpeed: Double,
        @SerializedName("weather")
        @Expose
        var weather: List<Weather>,
    )

    data class Weather(
        @SerializedName("main")
        @Expose
        var status : String,
        @SerializedName("description")
        @Expose
        var description : String,
        @SerializedName("icon")
        @Expose
        var icon:String
    )

    data class Daily(
        @SerializedName("dt")
        @Expose
        var dateTime : Long,
        @SerializedName("pressure")
        @Expose
        var pressure : Int,
        @SerializedName("humidity")
        @Expose
        var humidity : Int,
        @SerializedName("wind_speed")
        @Expose
        var windSpeed: Double,
        @SerializedName("weather")
        @Expose
        var weather: List<Weather>,
        @SerializedName("temp")
        @Expose
        var temp : Temp,
        @SerializedName("feels_like")
        @Expose
        var feelsLike: FeelsLike
    ){
        data class Temp(
            @SerializedName("min")
            @Expose
            var min : Double,
            @SerializedName("max")
            @Expose
            var max : Double
        )
        data class FeelsLike(
            @SerializedName("day")
            @Expose
            var day : Double,
            @SerializedName("night")
            @Expose
            var night : Double,
            @SerializedName("eve")
            @Expose
            var eve : Double,
            @SerializedName("morn")
            @Expose
            var morn : Double
        )
    }
}