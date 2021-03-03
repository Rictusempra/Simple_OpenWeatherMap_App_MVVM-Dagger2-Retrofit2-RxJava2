package com.weather.weathertestapp.di.modules

import com.weather.weathertestapp.remote.MyRetrofitFactory
import com.weather.weathertestapp.remote.weather.FakeCitiesApi
import com.weather.weathertestapp.remote.weather.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class RemoteModule() {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitFactory(okHttpClient: OkHttpClient): MyRetrofitFactory {
        return MyRetrofitFactory(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofitFactory: MyRetrofitFactory): WeatherApi {
        return retrofitFactory.getRetrofit("https://api.openweathermap.org").create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFakeCitiesApi(): FakeCitiesApi {
        return FakeCitiesApi()
    }

}
