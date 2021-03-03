package com.weather.weathertestapp.remote.weather

import com.weather.weathertestapp.data.CityItem
import com.weather.weathertestapp.data.SearchCityItemModel
import com.weather.weathertestapp.utils.JsonHelper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.weather.weathertestapp.R
import io.reactivex.Single

class FakeCitiesApi {
    fun getCitiesNames() : Single<SearchCityItemModel>{
        val list:MutableList<CityItem> = ArrayList<CityItem>()
        val response = JsonHelper().getJsonStringFromRaw(R.raw.cities_response)
        val convertedObject = Gson().fromJson(response, JsonObject::class.java).getAsJsonObject()
        val jsonCitiesArray = convertedObject.getAsJsonArray("cities")

        for (item in jsonCitiesArray) {
            list.add(CityItem(item.toString().replace("\"", ""), false))
        }

        val singleCitySearchItemModel = Single.create<SearchCityItemModel>{
            val citySearchItemModel = SearchCityItemModel(list as ArrayList<CityItem>)
            it.onSuccess(citySearchItemModel)
        }

        return singleCitySearchItemModel
    }
}