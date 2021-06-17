package com.lloyd.entersekt.ui.base.listeners

import com.lloyd.entersekt.data.models.cities.City

interface CityItemListener {
    fun onItemSelected(city : City)
}