package com.weather.weathertestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.weathertestapp.data.WeatherResponseModel
import com.weather.weathertestapp.remote.weather.WeatherApi
import com.weather.weathertestapp.utils.InternetHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CityWeatherViewModel(var weatherApi: WeatherApi) : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableLiveData: MutableLiveData<WeatherResponseModel> = MutableLiveData<WeatherResponseModel>()
    private val isLoading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<String>()
    private val OPENWEATHERMAP_API_KEY = "eadae8ebbf93b01e15a5c1fab26c8b26"

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getCityWeatherInfo(cityName: String): MutableLiveData<WeatherResponseModel> {

        isLoading.value = true

            compositeDisposable.add(
                weatherApi.getCityWeatherByCityName(cityName, OPENWEATHERMAP_API_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        it.weatherMain.temp = getTemperatureInCelsius(it.weatherMain.temp)
                        it.weatherMain.pressure = getPressureInMMHC(it.weatherMain.pressure)
                        mutableLiveData.setValue(it)

                        isLoading.value = false

                    },
                        {
                            error.setValue(it.message)
                            isLoading.setValue(false)
                        })
            )

        return mutableLiveData
    }

    fun getTemperatureInCelsius(temp: Float): Float {
        val tempC = temp - 273.15f
        return tempC
    }

    fun getPressureInMMHC(pressurePa: Int): Int {
        val pressureMMHC = pressurePa / 1.333f
        return pressureMMHC.toInt()
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return isLoading
    }

    fun getError(): MutableLiveData<String> {
        return error
    }

}