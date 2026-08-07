package com.example.googlemapcompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.googlemapcompose.GoogleMapViewModel

@Composable
fun GoogleMapRouter(viewModel: GoogleMapViewModel = hiltViewModel()){

    val uiState by viewModel.uiState.collectAsState()

    GoogleMapScreen(
        isLoading = uiState.isLoading,
        latLng = uiState.latLng,
        getRoute = { currentLatLng, destinationLatLng ->
            viewModel.getGraphhopperRoute(currentLatLng, destinationLatLng)
        }
    )
}