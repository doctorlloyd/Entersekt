package com.lloyd.entersekt.data

import com.lloyd.entersekt.data.models.cards.Cards
import com.lloyd.entersekt.data.models.sets.Sets
import com.lloyd.entersekt.data.models.cities.Cities
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestCities(): Flow<Resource<Cities>>
}
