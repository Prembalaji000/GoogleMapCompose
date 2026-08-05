package com.example.googlemapcompose

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class LocationHelper(
    private val context: Context
) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        onLocationReceived: (LatLng) -> Unit
    ) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    val latLng = LatLng(
                        it.latitude,
                        it.longitude
                    )
                    onLocationReceived(latLng)
                }
            }
    }
}