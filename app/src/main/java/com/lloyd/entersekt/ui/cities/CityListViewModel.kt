package com.lloyd.entersekt.ui.cities

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lloyd.entersekt.data.DataRepositorySource
import com.lloyd.entersekt.data.Resource
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.data.models.cities.Cities
import com.lloyd.entersekt.ui.base.BaseViewModel
import com.lloyd.entersekt.utils.SingleEvent
import com.lloyd.entersekt.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityListViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val citiesLiveDataPrivate = MutableLiveData<Resource<Cities>>()
    val citiesLiveData: LiveData<Resource<Cities>> get() = citiesLiveDataPrivate


    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openCityDetailsPrivate = MutableLiveData<SingleEvent<City>>()
    val openCityDetails: LiveData<SingleEvent<City>> get() = openCityDetailsPrivate

    /**
     * Error handling as UI
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getCities() {
        viewModelScope.launch {
            citiesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestCities().collect {
                    citiesLiveDataPrivate.value = it
                }
            }
        }
    }

    fun openCityDetails(city: City) {
        openCityDetailsPrivate.value = SingleEvent(city)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}