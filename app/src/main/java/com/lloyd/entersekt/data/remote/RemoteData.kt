package com.lloyd.entersekt.data.remote

import com.lloyd.entersekt.data.Resource
import com.lloyd.entersekt.data.error.NETWORK_ERROR
import com.lloyd.entersekt.data.error.NO_INTERNET_CONNECTION
import com.lloyd.entersekt.data.models.cities.Cities
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.data.remote.service.EntersektService
import com.lloyd.entersekt.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) :
    RemoteDataSource {

    override suspend fun requestCities(): Resource<Cities> {
        val entersektService = serviceGenerator.createService(EntersektService::class.java)
        return when (val response = processCall(entersektService::fetchCities)) {
            is Cities -> {
                Resource.Success(data = response as Cities)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }

        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
