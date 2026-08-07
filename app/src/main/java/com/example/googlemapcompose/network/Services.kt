package com.example.googlemapcompose.network

import com.example.googlemapcompose.models.DirectionsResponse
import com.example.googlemapcompose.models.RouteRequest
import com.example.googlemapcompose.models.GraphHopperResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("maps/api/directions/json")
    suspend fun getGoogleDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): DirectionsResponse

    @POST("api/1/route")
    suspend fun getGraphhopperRoute(
        @Query("key") key: String,
        @Body requestedBody: RouteRequest
    ): GraphHopperResponse
}