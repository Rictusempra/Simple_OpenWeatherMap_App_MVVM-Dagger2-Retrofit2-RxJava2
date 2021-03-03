package com.weather.weathertestapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

}
