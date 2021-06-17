package com.lloyd.entersekt.data.models.cards

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Legality(
    @Json(name = "format")val format: String = "",
    @Json(name = "legality")val legality: String = ""
) : Parcelable