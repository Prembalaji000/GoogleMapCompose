package com.example.googlemapcompose.models

import com.google.gson.annotations.SerializedName

data class DirectionsResponse(
    val routes: List<Route>
)


data class Route(
    @SerializedName("overview_polyline")
    val overviewPolyline: Polyline
)


data class Polyline(
    val points: String
)