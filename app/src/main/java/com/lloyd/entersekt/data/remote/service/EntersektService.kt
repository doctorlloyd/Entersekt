package com.lloyd.entersekt.data.remote.service

import com.lloyd.entersekt.data.models.cities.Cities
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EntersektService {

    @GET("cities/")
    suspend fun fetchCities(): Response<Cities>
}