package com.lloyd.entersekt.ui.cities.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lloyd.entersekt.R
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.databinding.CityLayoutBinding
import com.lloyd.entersekt.ui.base.listeners.CityItemListener
import com.squareup.picasso.Picasso

class CityViewHolder (private val itemBinding: CityLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(city: City, cityItemListener: CityItemListener) {
        itemBinding.tvName.text = city.name
        itemBinding.tvCaption.text = "Number of malls: ${city.malls.size}"
        itemBinding.rlCityItem.setOnClickListener { cityItemListener.onItemSelected(city) }
    }
}