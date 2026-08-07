package com.example.googlemapcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlemapcompose.models.RouteRequest
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleMapViewModel @Inject constructor(
    private val repository : Repository
): ViewModel() {

    private val viewModelState = MutableStateFlow(
        MyViewModelState(isLoading = false)
    )

    val uiState = viewModelState
        .map { it.uiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.uiState())

    init {
        //getDirection()
        //getGraphhopperRoute()
    }
    fun getGraphhopperRoute(currentLatLng: LatLng?, destinationLatLng: LatLng?){
        viewModelScope.launch {
            currentLatLng ?: return@launch
            destinationLatLng ?: return@launch
            viewModelState.update { it.copy(isLoading = true) }
            val request = RouteRequest(
                points = listOf(
                    listOf(
                        currentLatLng.longitude,
                        currentLatLng.latitude
                    ),
                    listOf(
                        destinationLatLng.longitude,
                        destinationLatLng.latitude
                    )
                ),
                snapPreventions = listOf(
                    "motorway",
                    "ferry",
                    "tunnel"
                ),
                details = listOf(
                    "road_class",
                    "surface"
                ),
                profile = "car",
                locale = "en",
                instructions = true,
                calcPoints = true,
                pointsEncoded = false
            )
            val response = repository.getGraphhopperRoute(
                requestedBody = request
            )
            val latLng = response.paths.flatMap { path ->
                path.points.coordinates.map { coordinate ->
                    LatLng(
                        coordinate[1],
                        coordinate[0]
                    )
                }
            }
            viewModelState.update {
                it.copy(
                    isLoading = false,
                    latLng = latLng
                )
            }
        }
    }
}

data class MyViewModelState(
    val isLoading: Boolean? = false,
    val latLng: List<LatLng>? = listOf()
){
    fun uiState() = MyUiState(
        isLoading = isLoading,
        latLng = latLng
    )
}

data class MyUiState(
    val isLoading: Boolean?,
    val latLng: List<LatLng>?
)