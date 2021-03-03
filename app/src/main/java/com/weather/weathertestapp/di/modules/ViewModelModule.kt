package com.weather.weathertestapp.di.modules

import com.weather.weathertestapp.remote.weather.FakeCitiesApi
import com.weather.weathertestapp.remote.weather.WeatherApi
import com.weather.weathertestapp.ui.viewmodel.CityWeatherViewModel
import com.weather.weathertestapp.ui.viewmodel.SearchCityViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RemoteModule::class])
class ViewModelModule {

    @Provides
    @Singleton
    fun provideCityWeatherViewModel(weatherApi: WeatherApi): CityWeatherViewModel {
        return CityWeatherViewModel(weatherApi)
    }

    @Provides
    @Singleton
    fun provideSearchCityViewModel(fakeCitiesApi: FakeCitiesApi): SearchCityViewModel {
        return SearchCityViewModel(fakeCitiesApi)
    }

}
