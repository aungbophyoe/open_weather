package com.aungbophyoe.space.openweather.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeatherNetworkEntity(
    @SerializedName("id")
    @Expose
    var id : Int,
    @SerializedName("name")
    @Expose
    var name : String,
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>,
    @SerializedName("main")
    @Expose
    var detail: Detail,
    @SerializedName("dt")
    @Expose
    var dateTime : Long,
    @SerializedName("wind")
    @Expose
    var wind : Wind
    ) {
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

    data class Wind(
        @SerializedName("speed")
        @Expose
        var speed : Double,
    )

    data class Detail(
        @SerializedName("temp")
        @Expose
        var temp : Double,
        @SerializedName("temp_min")
        @Expose
        var tempMin : Double,
        @SerializedName("temp_max")
        @Expose
        var tempMax : Double,
        @SerializedName("feels_like")
        @Expose
        var feelsLike : Double,
        @SerializedName("pressure")
        @Expose
        var pressure : Int,
        @SerializedName("humidity")
        @Expose
        var humidity : Int,
    )
}