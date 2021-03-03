package com.weather.weathertestapp.remote.weather

import com.weather.weathertestapp.data.WeatherResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather?")
    @Headers("Content-Type: application/json")
    fun getCityWeatherByCityName(@Query("q") cityName: String, @Query("appid") api_key: String) : Single<WeatherResponseModel>

    @GET("/data/2.5/weather?")
    @Headers("Content-Type: application/json")
    fun getCityWeatherByCoordinates(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") api_key: String) : Single<WeatherResponseModel>

}