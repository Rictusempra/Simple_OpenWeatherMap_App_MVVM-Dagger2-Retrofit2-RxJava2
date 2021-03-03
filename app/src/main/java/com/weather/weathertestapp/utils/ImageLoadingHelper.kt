package com.weather.weathertestapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.weather.weathertestapp.App.Companion.appComponent
import javax.inject.Inject

class ImageLoadingHelper() {

    @Inject
   lateinit var context: Context

    init {
        appComponent.inject(this)
    }

fun loadImage(imageUrl: String, imageView: ImageView) {
    Glide.with(context)
        .load(imageUrl)
        .fitCenter()
        .into(imageView)
}

}