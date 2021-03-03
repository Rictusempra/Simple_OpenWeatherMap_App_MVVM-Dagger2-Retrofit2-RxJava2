package com.weather.weathertestapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.weathertestapp.data.CityItem
import com.weather.weathertestapp.databinding.ItemCityListBinding
import java.util.*

class SearchCitiesRVAdapter(): RecyclerView.Adapter<SearchCitiesRVAdapter.FavoriteCityViewHolder>() {

    lateinit var binding: ItemCityListBinding
    private var allCitiesSearchItem: ArrayList<CityItem> = ArrayList<CityItem>()
    private var filteredCitiesSearchItem: ArrayList<CityItem> = ArrayList<CityItem>()
    private var query:String = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCityViewHolder {
        binding = ItemCityListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteCityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCityViewHolder, position: Int) {
        val item = filteredCitiesSearchItem.get(position)
        holder.bindFavoriteCitiesListItem(item)
    }

    class FavoriteCityViewHolder(private var view: ItemCityListBinding) : RecyclerView.ViewHolder(view.root){

        private lateinit var citySearchItemModelItem: CityItem

        fun bindFavoriteCitiesListItem(item: CityItem) {
            this.citySearchItemModelItem = item
            view.searchCityListName.setText(item.cityName)
            view.favoriteButton.setFavorite(item.isFavorite)
            view.itemCityContainer.setTag(view.itemCityContainer.id, item.cityName)
        }

    }

    private fun filterCitiesList() {
        filteredCitiesSearchItem.clear()
        if (query.isEmpty()) {
            filteredCitiesSearchItem.addAll(allCitiesSearchItem)
        } else {
            for (city in allCitiesSearchItem) {
                if (city.cityName.toLowerCase().contains(query.toLowerCase())) {
                    filteredCitiesSearchItem.add(city)
                }
            }
        }
    }

    fun setAllCitiesList(allCitiesSearchItem: ArrayList<CityItem>) {
        this.allCitiesSearchItem = allCitiesSearchItem
    }

    fun setQuery(query: String) {
        this.query = query
        filterCitiesList()
    }

    override fun getItemCount(): Int {
        return filteredCitiesSearchItem.size
    }

}