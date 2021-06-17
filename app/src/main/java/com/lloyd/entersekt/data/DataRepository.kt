package com.lloyd.entersekt.data

import com.lloyd.entersekt.data.models.cards.Cards
import com.lloyd.entersekt.data.models.sets.Sets
import com.lloyd.entersekt.data.models.cities.Cities
import com.lloyd.entersekt.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) :
    DataRepositorySource {

    override suspend fun requestCities(): Flow<Resource<Cities>> {
        return flow {
            emit(remoteRepository.requestCities())
        }.flowOn(ioDispatcher)
    }
}
