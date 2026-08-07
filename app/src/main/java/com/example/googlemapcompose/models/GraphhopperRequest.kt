package com.example.googlemapcompose.models

import com.google.gson.annotations.SerializedName

data class RouteRequest(
    val points: List<List<Double>>,
    @SerializedName("snap_preventions")
    val snapPreventions: List<String>,
    val details: List<String>,
    val profile: String,
    val locale: String,
    val instructions: Boolean,
    @SerializedName("calc_points")
    val calcPoints: Boolean,
    @SerializedName("points_encoded")
    val pointsEncoded: Boolean
)