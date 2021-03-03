package com.weather.weathertestapp.data

import com.google.gson.annotations.SerializedName

data class WeatherResponseModel (

    @SerializedName("main")
    val weatherMain: WeatherMain,

    @SerializedName("weather")
    val weatherDescription: List<WeatherDescription>,

    @SerializedName("name")
    val name: String

    ) { }


data class WeatherMain(
    @SerializedName("temp")
    var temp: Float = 0f,

    @SerializedName("pressure")
    var pressure: Int = 0,

    @SerializedName("humidity")
    val humidity: Int = 0

) { }

data class WeatherDescription(
    @SerializedName("icon")
    val icon: String
) { }