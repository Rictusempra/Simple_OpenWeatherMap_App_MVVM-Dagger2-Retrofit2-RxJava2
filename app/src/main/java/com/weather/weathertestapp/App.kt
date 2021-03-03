package com.weather.weathertestapp

import android.app.Application
import android.content.Context
import com.weather.weathertestapp.di.components.AppComponent
import com.weather.weathertestapp.di.modules.AppModule
import com.weather.weathertestapp.di.components.DaggerAppComponent

open class App : Application() {

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}
