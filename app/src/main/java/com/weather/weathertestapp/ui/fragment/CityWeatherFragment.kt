package com.weather.weathertestapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import com.weather.weathertestapp.App.Companion.appComponent
import com.weather.weathertestapp.databinding.FragmentCityWeatherBinding
import com.weather.weathertestapp.ui.viewmodel.CityWeatherViewModel
import com.weather.weathertestapp.utils.ImageLoadingHelper
import javax.inject.Inject


class CityWeatherFragment : BaseFragment() {

    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var cityWeatherViewModel: CityWeatherViewModel

    lateinit var binding: FragmentCityWeatherBinding

    private val CITY_NAME = "Kaliningrad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityWeatherViewModel.getCityWeatherInfo(CITY_NAME).observe(viewLifecycleOwner) { it ->

            binding.cityName.setText(it.name)
            binding.temperature.setText(it.weatherMain.temp.toInt().toString() + " C")
            binding.pressure.setText(it.weatherMain.pressure.toString() + " mmHc")
            binding.humidity.setText(it.weatherMain.humidity.toString() + " %")

            ImageLoadingHelper().loadImage("https://openweathermap.org/img/wn/${it.weatherDescription.get(0).icon}@2x.png", binding.weatherIcon)
        }

        cityWeatherViewModel.getIsLoading().observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else binding.loadingIndicator.visibility = View.GONE
        }

        cityWeatherViewModel.getError().observe(viewLifecycleOwner) {
            Log.e("ERROR:", it)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        binding.favoriteButton.setOnFavoriteChangeListener { buttonView, favorite ->
            if (favorite) {
                //
            } else {
                //
            }
        }

        binding.allFavoriteCitiesButton.setOnClickListener {
            //
        }
    }

}


