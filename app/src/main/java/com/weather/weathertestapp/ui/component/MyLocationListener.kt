package com.weather.weathertestapp.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class MyLocationListener : LocationListener {

    override fun onLocationChanged(loc: Location) {
        imHere = loc
    }
    override fun onProviderDisabled(provider: String) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

    companion object {
        var imHere: Location? = null
    }

    @SuppressLint("MissingPermission")
    fun SetUpLocationListener(context: Context) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener: LocationListener = MyLocationListener()
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            1000, 10f, locationListener)
        imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    }
}