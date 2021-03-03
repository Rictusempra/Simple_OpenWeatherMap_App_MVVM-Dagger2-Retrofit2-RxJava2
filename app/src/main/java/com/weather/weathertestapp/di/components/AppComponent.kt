package com.weather.weathertestapp.di.components

import com.weather.weathertestapp.di.modules.AppModule
import com.weather.weathertestapp.di.modules.RemoteModule
import com.weather.weathertestapp.di.modules.ViewModelModule
import com.weather.weathertestapp.ui.fragment.CityWeatherFragment
import com.weather.weathertestapp.ui.fragment.SearchCityFragment
import com.weather.weathertestapp.ui.viewmodel.SearchCityViewModel
import com.weather.weathertestapp.ui.viewmodel.CityWeatherViewModel
import com.weather.weathertestapp.utils.ImageLoadingHelper
import com.weather.weathertestapp.utils.InternetHelper
import com.weather.weathertestapp.utils.JsonHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: CityWeatherFragment)
    fun inject(cityWeatherViewModel: CityWeatherViewModel)
    fun inject(searchCityViewModel: SearchCityViewModel)
    fun inject(searchCityFragment: SearchCityFragment)
    fun inject(imageLoadingHelper: ImageLoadingHelper)
    fun inject(jsonHelper: JsonHelper)
    fun inject(internetHelper: InternetHelper)
}
