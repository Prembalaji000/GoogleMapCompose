package com.example.googlemapcompose

import com.example.googlemapcompose.Constants.GOOGLE_MAP_DIRECTION_API_KEY
import com.example.googlemapcompose.Constants.GRAPHHOPPER_MAP_ROUTE_API_KEY
import com.example.googlemapcompose.models.DirectionsResponse
import com.example.googlemapcompose.models.GraphHopperResponse
import com.example.googlemapcompose.models.RouteRequest
import com.example.googlemapcompose.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService
) {
    suspend fun getGoogleDirection(): DirectionsResponse {
        return service.getGoogleDirection(
            origin = "chennai",
            destination = "velacheery",
            apiKey = GOOGLE_MAP_DIRECTION_API_KEY
        )
    }

    suspend fun getGraphhopperRoute(requestedBody: RouteRequest): GraphHopperResponse {
        return service.getGraphhopperRoute(
            key = GRAPHHOPPER_MAP_ROUTE_API_KEY,
            requestedBody = requestedBody
        )
    }
}