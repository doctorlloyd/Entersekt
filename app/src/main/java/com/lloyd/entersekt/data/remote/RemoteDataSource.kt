package com.lloyd.entersekt.data.remote

import com.lloyd.entersekt.data.models.cards.Cards
import com.lloyd.entersekt.data.Resource
import com.lloyd.entersekt.data.models.sets.Sets
import com.lloyd.entersekt.data.models.cities.Cities

internal interface RemoteDataSource {
    suspend fun requestCities(): Resource<Cities>
}
