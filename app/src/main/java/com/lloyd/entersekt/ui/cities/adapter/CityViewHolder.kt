package com.lloyd.entersekt.ui.cities.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lloyd.entersekt.R
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.databinding.CityLayoutBinding
import com.lloyd.entersekt.ui.base.listeners.CityItemListener
import com.squareup.picasso.Picasso

class CityViewHolder (private val itemBinding: CityLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(city: City, cityItemListener: CityItemListener) {
        itemBinding.tvCaption.text = city.name
        itemBinding.tvName.text = city.name
//        Picasso.get().load(city.imageUrl).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).into(itemBinding.ivCityItemImage)
        itemBinding.rlCityItem.setOnClickListener { cityItemListener.onItemSelected(city) }
    }
}