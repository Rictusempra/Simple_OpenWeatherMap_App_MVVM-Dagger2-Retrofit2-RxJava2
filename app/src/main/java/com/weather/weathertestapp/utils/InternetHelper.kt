package com.weather.weathertestapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.weather.weathertestapp.App
import javax.inject.Inject

class InternetHelper() {

    @Inject
    lateinit var context: Context

    init {
        App.appComponent.inject(this)
    }

    fun isConnectedToInternet(): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}