package com.example.googlemapcompose.models

import com.google.gson.annotations.SerializedName

data class GraphHopperResponse(
    val info: Info,
    val paths: List<Path>
)


data class Info(
    val copyrights: List<String>,
    val took: Int,
    @SerializedName("road_data_timestamp")
    val roadDataTimestamp: String
)

data class Path(
    val distance: Double,
    val weight: Int,
    val time: Long,
    val transfers: Int,
    val legs: List<Any>,
    @SerializedName("points_encoded")
    val pointsEncoded: Boolean,
    @SerializedName("bbox")
    val box: List<Double>,
    val points: Geometry,
    val instructions: List<Instruction>,
    val details: Details,
    val ascend: Double,
    val descend: Double,
    @SerializedName("snapped_waypoints")
    val snappedWaypoints: Geometry
)

data class Geometry(
    val type: String,
    val coordinates: List<List<Double>>
)

data class Instruction(
    @SerializedName("street_ref")
    val streetRef: String? = null,
    val distance: Double,
    val heading: Double? = null,
    val sign: Int,
    val interval: List<Int>,
    val text: String,
    val time: Long,
    @SerializedName("street_name")
    val streetName: String,
    @SerializedName("last_heading")
    val lastHeading: Double? = null
)

data class Details(
    val surface: List<List<Any>>,
    @SerializedName("road_class")
    val roadClass: List<List<Any>>
)