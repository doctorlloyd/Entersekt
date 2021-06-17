package com.lloyd.entersekt.ui.cityDetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.ui.base.BaseViewModel
import com.lloyd.entersekt.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor() : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val cityPrivate = MutableLiveData<City>()
    val cityData: LiveData<City> get() = cityPrivate

    fun initIntentData(city: City) {
        cityPrivate.value = city
    }
}