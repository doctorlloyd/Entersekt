package com.lloyd.entersekt.ui.cities

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lloyd.entersekt.ITEM_KEY
import com.lloyd.entersekt.R
import com.lloyd.entersekt.data.Resource
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.data.models.cities.Cities
import com.lloyd.entersekt.databinding.CityActivityBinding
import com.lloyd.entersekt.ui.base.BaseActivity
import com.lloyd.entersekt.ui.cityDetails.DetailsActivity
import com.lloyd.entersekt.ui.cities.adapter.CityAdapter
import com.lloyd.entersekt.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CityListActivity : BaseActivity() {
    private lateinit var binding: CityActivityBinding

    private val cityListViewModel: CityListViewModel by viewModels()
    private lateinit var cityAdapter: CityAdapter

    override fun initViewBinding() {
        binding = CityActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.city)
        val layoutManager = LinearLayoutManager(this)
        binding.rvCityList.layoutManager = layoutManager
        binding.rvCityList.setHasFixedSize(true)
        cityListViewModel.getCities()
    }

    private fun bindListData(list: Cities) {
        if (list.cities.isNotEmpty()) {
            cityAdapter = CityAdapter(cityListViewModel, list.cities)
            binding.rvCityList.adapter = cityAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<City>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(ITEM_KEY, it)
            }
            startActivity(nextScreenIntent)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) GONE else VISIBLE
        binding.rvCityList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvCityList.toGone()
    }

    private fun handleCityList(status: Resource<Cities>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(list = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { cityListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(cityListViewModel.citiesLiveData, ::handleCityList)
        observeEvent(cityListViewModel.openCityDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(cityListViewModel.showSnackBar)
        observeToast(cityListViewModel.showToast)

    }
}