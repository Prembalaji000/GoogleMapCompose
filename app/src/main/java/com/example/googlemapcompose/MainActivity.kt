package com.example.googlemapcompose

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.googlemapcompose.ui.theme.GoogleMapComposeTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.ComposeMapColorScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoogleMapComposeTheme {
                GoogleMapScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoogleMapComposeTheme {
        GoogleMapScreen()
    }
}


@OptIn(MapsComposeExperimentalApi::class)
@SuppressLint("UnrememberedMutableState", "ConfigurationScreenWidthHeight")
@Composable
fun GoogleMapScreen(){
    val context = LocalContext.current
    var currentLocation by remember {
        mutableStateOf<LatLng?>(null)
    }
    LocationPermission {
        LocationHelper(context)
            .getCurrentLocation { latLng ->
                currentLocation = latLng
            }
    }
    val defaultPosition = LatLng(12.9716, 77.5946)
    val velloreToBangaloreRoute = listOf(
        LatLng(12.9165, 79.1325), // Vellore
        LatLng(12.8500, 78.9500),
        LatLng(12.7800, 78.7500),
        LatLng(12.6500, 78.5500),
        LatLng(12.5700, 78.3000),
        LatLng(12.4800, 78.1500),
        LatLng(12.5200, 77.9500),
        LatLng(12.4300, 77.8000),
        LatLng(12.3850, 77.7000),
        LatLng(12.2958, 77.6394),
        LatLng(12.3500, 77.6500),
        LatLng(12.4500, 77.5800),
        LatLng(12.5500, 77.6000),
        LatLng(12.7000, 77.5800),
        LatLng(12.8500, 77.5900),
        LatLng(12.9716, 77.5946) // Bangalore
    )
    val locations = listOf(
        MapLocationDetails(
            latLng = LatLng(13.0827, 80.2707),
            title = "Chennai",
            snippet = "Capital city of Tamil Nadu"
        ),
        MapLocationDetails(
            latLng = LatLng(12.9165, 79.1325),
            title = "Vellore",
            snippet = ""
        ),
        MapLocationDetails(
            latLng = LatLng(12.9716, 77.5946),
            title = "Bengaluru",
            snippet = "Silicon Valley of India"
        ),
        MapLocationDetails(
            latLng = LatLng(17.3850, 78.4867),
            title = "Hyderabad",
            snippet = "City of Pearls"
        ),
        MapLocationDetails(
            latLng = LatLng(19.0760, 72.8777),
            title = "Mumbai",
            snippet = "Financial capital of India"
        ),
        MapLocationDetails(
            latLng = LatLng(28.6139, 77.2090),
            title = "New Delhi",
            snippet = "Capital of India"
        ),
        MapLocationDetails(
            latLng = LatLng(22.5726, 88.3639),
            title = "Kolkata",
            snippet = "City of Joy"
        ),
        MapLocationDetails(
            latLng = LatLng(18.5204, 73.8567),
            title = "Pune",
            snippet = "Oxford of the East"
        ),
        MapLocationDetails(
            latLng = LatLng(23.0225, 72.5714),
            title = "Ahmedabad",
            snippet = "Heritage City of India"
        ),
        MapLocationDetails(
            latLng = LatLng(26.9124, 75.7873),
            title = "Jaipur",
            snippet = "Pink City"
        ),
        MapLocationDetails(
            latLng = LatLng(20.2961, 85.8245),
            title = "Bhubaneswar",
            snippet = "Temple City of India"
        ),
        MapLocationDetails(
            latLng = LatLng(17.6868, 83.2185),
            title = "Visakhapatnam",
            snippet = "Port City of Andhra Pradesh"
        ),
        MapLocationDetails(
            latLng = LatLng(16.5062, 80.6480),
            title = "Vijayawada",
            snippet = "Commercial hub of Andhra Pradesh"
        ),
        MapLocationDetails(
            latLng = LatLng(9.9312, 76.2673),
            title = "Kochi",
            snippet = "Queen of the Arabian Sea"
        ),
        MapLocationDetails(
            latLng = LatLng(11.0168, 76.9558),
            title = "Coimbatore",
            snippet = "Manchester of South India"
        ),
        MapLocationDetails(
            latLng = LatLng(9.9252, 78.1198),
            title = "Madurai",
            snippet = "Temple City"
        ),
        MapLocationDetails(
            latLng = LatLng(10.7905, 78.7047),
            title = "Tiruchirappalli",
            snippet = "Home of Rockfort Temple"
        ),
        MapLocationDetails(
            latLng = LatLng(11.6643, 78.1460),
            title = "Salem",
            snippet = "Steel City of Tamil Nadu"
        ),
        MapLocationDetails(
            latLng = LatLng(8.0883, 77.5385),
            title = "Kanyakumari",
            snippet = "Southern tip of India"
        ),
        MapLocationDetails(
            latLng = LatLng(30.7333, 76.7794),
            title = "Chandigarh",
            snippet = "The City Beautiful"
        ),
        MapLocationDetails(
            latLng = LatLng(34.0837, 74.7973),
            title = "Srinagar",
            snippet = "Summer capital of Jammu & Kashmir"
        ),
        MapLocationDetails(
            LatLng(21.1458, 79.0882),
            "Nagpur",
            "Orange City of India"
        ),
        MapLocationDetails(
            LatLng(22.7196, 75.8577),
            "Indore",
            "Commercial capital of Madhya Pradesh"
        ),
        MapLocationDetails(
            LatLng(23.2599, 77.4126),
            "Bhopal",
            "City of Lakes"
        ),
        MapLocationDetails(
            LatLng(25.5941, 85.1376),
            "Patna",
            "Capital of Bihar"
        ),
        MapLocationDetails(
            LatLng(26.8467, 80.9462),
            "Lucknow",
            "City of Nawabs"
        ),
        MapLocationDetails(
            LatLng(26.4499, 80.3319),
            "Kanpur",
            "Leather City of India"
        ),
        MapLocationDetails(
            LatLng(27.1767, 78.0081),
            "Agra",
            "Home of Taj Mahal"
        ),
        MapLocationDetails(
            LatLng(25.3176, 82.9739),
            "Varanasi",
            "Spiritual capital of India"
        ),
        MapLocationDetails(
            LatLng(24.5854, 73.7125),
            "Udaipur",
            "City of Lakes"
        ),
        MapLocationDetails(
            LatLng(26.2389, 73.0243),
            "Jodhpur",
            "Blue City of India"
        ),
        MapLocationDetails(
            LatLng(25.2138, 75.8648),
            "Kota",
            "Education hub of India"
        ),
        MapLocationDetails(
            LatLng(31.6340, 74.8723),
            "Amritsar",
            "Home of Golden Temple"
        ),
        MapLocationDetails(
            LatLng(30.3165, 78.0322),
            "Dehradun",
            "Capital of Uttarakhand"
        ),
        MapLocationDetails(
            LatLng(32.7266, 74.8570),
            "Jammu",
            "Winter capital of Jammu & Kashmir"
        ),
        MapLocationDetails(
            LatLng(27.4924, 77.6737),
            "Mathura",
            "Birthplace of Lord Krishna"
        ),
        MapLocationDetails(
            LatLng(15.2993, 74.1240),
            "Goa",
            "Beach destination of India"
        ),
        MapLocationDetails(
            LatLng(19.9975, 73.7898),
            "Nashik",
            "Wine capital of India"
        ),
        MapLocationDetails(
            LatLng(16.7050, 74.2433),
            "Kolhapur",
            "Historical city of Maharashtra"
        ),
        MapLocationDetails(
            LatLng(15.8281, 78.0373),
            "Kurnool",
            "Gateway of Rayalaseema"
        ),
        MapLocationDetails(
            LatLng(14.4426, 79.9865),
            "Nellore",
            "City of Prawns"
        ),
        MapLocationDetails(
            LatLng(15.9129, 79.7400),
            "Amaravati",
            "Capital region of Andhra Pradesh"
        ),
        MapLocationDetails(
            LatLng(11.1271, 78.6569),
            "Tamil Nadu",
            "State in South India"
        ),
        MapLocationDetails(
            LatLng(12.2958, 76.6394),
            "Mysuru",
            "City of Palaces"
        ),
        MapLocationDetails(
            LatLng(15.3173, 75.7139),
            "Hubballi",
            "Commercial hub of Karnataka"
        ),
        MapLocationDetails(
            LatLng(23.1815, 79.9864),
            "Jabalpur",
            "Marble Rocks city"
        ),
        MapLocationDetails(
            LatLng(21.1702, 72.8311),
            "Surat",
            "Diamond City of India"
        ),
        MapLocationDetails(
            LatLng(22.3072, 73.1812),
            "Vadodara",
            "Cultural city of Gujarat"
        ),
        MapLocationDetails(
            LatLng(20.3893, 78.1307),
            "Yavatmal",
            "City in Maharashtra"
        ),
        MapLocationDetails(
            LatLng(20.9374, 77.7796),
            "Amravati",
            "City in Maharashtra"
        ),
        MapLocationDetails(
            LatLng(19.9975, 73.7898),
            "Aurangabad",
            "Historical city of Maharashtra"
        )
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation?:defaultPosition, 6f)
    }
    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp.dp
    val width = configuration.screenWidthDp.dp
    val clusterAlgorithm = NonHierarchicalViewBasedAlgorithm<Cluster>(
        height.value.toInt(),
        width.value.toInt()
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = true,
                compassEnabled = true,
                myLocationButtonEnabled = true,
                mapToolbarEnabled = true,
                zoomGesturesEnabled = true
            ),
            properties = MapProperties(
                mapType = MapType.NORMAL,
                isBuildingEnabled = true,
                isIndoorEnabled = true,
                isMyLocationEnabled = true,
                isTrafficEnabled = true,
                /*mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.map_style
                )*/
            ),
            mapColorScheme = ComposeMapColorScheme.DARK,
        ) {
            val clusterManager = rememberClusterManager<Cluster>()
            clusterManager?.let { manager ->
                val render = rememberClusterRenderer(
                    clusterManager = manager,
                )
                manager.algorithm = clusterAlgorithm
                SideEffect {
                    if (manager.renderer != render){
                        manager.renderer = render ?: return@SideEffect
                    }
                }
                Clustering(
                    items = locations.map {
                        Cluster(
                            latLng = it.latLng,
                            title = it.title,
                            snippet = it.snippet
                        )
                    },
                    clusterManager = manager
                )
            }

            Polyline(
                points = velloreToBangaloreRoute,
                color = Color.Red,
                width = 10f
            )

            Circle(
                center = defaultPosition,
                radius = 50000.0,
                fillColor = Color.Red.copy(alpha = 0.4f),
                strokeColor = Color.Transparent
            )

            Circle(
                center = currentLocation?: defaultPosition,
                radius = 1000.0,
                fillColor = Color.Blue.copy(alpha = 0.2f),
                strokeColor = Color.Transparent
            )

            /*Marker(
                state = MarkerState(currentLocation?:defaultPosition),
                zIndex = 1f,
                icon = BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                title = "Me",
                snippet = "Your Current Location"
            )*/

            /* locations.forEach {
                 if (it.latLng == defaultPosition){
                     Marker(
                         state = MarkerState(position = it.latLng),
                         title = it.title,
                         snippet = it.snippet,
                         zIndex = 12f
                     )
                     Circle(
                         center = defaultPosition,
                         radius = 50000.0,
                         fillColor = Color.Red.copy(alpha = 0.6f),
                         strokeColor = Color.Transparent
                     )
                 } else {
                     Marker(
                         state = MarkerState(position = it.latLng),
                         title = it.title,
                         snippet = it.snippet
                     )
                 }
             }*/
        }
    }
}

@Composable
fun LocationPermission(
    onPermissionGranted: () -> Unit
) {
    val context = LocalContext.current
    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val granted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            if (granted) {
                onPermissionGranted()
            } else {
                Toast.makeText(context, "Storage permission is required", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context.packageName, null)
                )
                context.startActivity(intent)
            }
        }


    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}


data class MapLocationDetails(
    val latLng: LatLng,
    val title: String,
    val snippet: String
)
