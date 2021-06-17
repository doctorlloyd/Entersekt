package com.lloyd.entersekt.data.models.cities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Shop(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = ""
): Parcelable