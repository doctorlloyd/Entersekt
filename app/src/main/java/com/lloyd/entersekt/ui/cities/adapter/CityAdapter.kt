package com.lloyd.entersekt.ui.cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.databinding.CityLayoutBinding
import com.lloyd.entersekt.ui.base.listeners.CityItemListener
import com.lloyd.entersekt.ui.cities.CityListViewModel

class CityAdapter (private val cityListViewModel: CityListViewModel, private val cities: List<City>) : RecyclerView.Adapter<CityViewHolder>() {

    private val onItemClickListener: CityItemListener = object : CityItemListener {
        override fun onItemSelected(city: City) {
            cityListViewModel.openCityDetails(city)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemBinding = CityLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}