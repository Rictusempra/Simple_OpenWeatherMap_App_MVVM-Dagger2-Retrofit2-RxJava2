package com.weather.weathertestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.weathertestapp.data.SearchCityItemModel
import com.weather.weathertestapp.remote.weather.FakeCitiesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchCityViewModel(var fakeCitiesApi: FakeCitiesApi) : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val mutableLiveData: MutableLiveData<SearchCityItemModel> = MutableLiveData<SearchCityItemModel>()

    private val isLoading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<String>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getCities(): MutableLiveData<SearchCityItemModel> {
        isLoading.value = true

        compositeDisposable.add(
            fakeCitiesApi.getCitiesNames()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading.value = false
                    mutableLiveData.setValue(it)
                },
                    {
                        error.setValue(it.message)
                        isLoading.setValue(false)
                    })
        )

        return mutableLiveData
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return isLoading
    }

    fun getError(): MutableLiveData<String> {
        return error
    }
}