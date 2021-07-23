package com.example.platzi_challenge.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beer(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "first_brewed") val firstBrewed: String,
    @Json(name = "description") val description: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "abv") val abv: Double?,
    @Json(name = "ibu") val ibu: Double?,
    @Json(name = "ph") val ph: Double?,
    @Json(name = "brewers_tips") val brewersTips: String,
): Parcelable