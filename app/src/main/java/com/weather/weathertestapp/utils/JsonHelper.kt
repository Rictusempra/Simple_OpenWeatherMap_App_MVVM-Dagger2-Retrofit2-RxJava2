package com.weather.weathertestapp.utils

import android.content.Context
import com.weather.weathertestapp.App
import java.io.InputStreamReader
import javax.inject.Inject


class JsonHelper() {
    @Inject
    lateinit var context: Context

    init {
        App.appComponent.inject(this)
    }

    fun getJsonStringFromRaw(resourceId:Int): String {
        val iReader = InputStreamReader(context.getResources().openRawResource(resourceId))
        val buffer = CharArray(4096)
        val sb = StringBuilder()
        var len: Int
        while (iReader.read(buffer).also { len = it } > 0) {
            sb.append(buffer, 0, len)
        }
        return sb.toString()
    }

}