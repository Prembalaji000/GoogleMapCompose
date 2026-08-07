# Google Maps Compose

A modern Android application demonstrating advanced **Google Maps** integration using **Jetpack Compose**. This project serves as a comprehensive example of how to implement real-time location features, routing, and data visualization on a map.

## Features

- **Jetpack Compose UI**: Built entirely with modern declarative UI components.
- **Google Maps Integration**: Uses the official `maps-compose` library for seamless map rendering.
- **Directions & Routing**:
    - Integrated **Google Directions API** to fetch and render routes between locations.
    - Support for custom routing via **GraphHopper API**.
    - Polyline rendering for path visualization.
- **Marker Clustering**: Efficiently handles large numbers of markers using the Google Maps Clustering utility.
- **Real-time Location**:
    - Current location tracking with permission handling.
    - Camera animations to follow the user.
- **Map Visuals**: 
    - Custom Marker implementation.
    - Circle overlays for proximity visualization.
    - Dynamic Map Color Schemes (Light/Dark support).
- **Modern Architecture**:
    - **MVVM Pattern**: Clean separation of UI, Business Logic, and Data.
    - **Dependency Injection**: Fully powered by **Hilt**.
    - **Repository Pattern**: Centralized data management.
- **Networking**: **Retrofit** and **Gson** for robust API communication.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Navigation**: Compose Navigation
- **DI**: Dagger Hilt
- **Network**: Retrofit, OkHttp, Gson
- **Maps**: Google Maps SDK, Maps Compose Library
- **Async**: Kotlin Coroutines & Flow

## Setup & Installation

### Prerequisites
- Android Studio Ladybug (or newer)
- A Google Cloud Project with the following APIs enabled:
    - Maps SDK for Android
    - Directions API
    - Places API

### Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/googleMapCompose.git
   ```
2. **Add API Key:**
   Open `Repository.kt` and replace the placeholder API key with your own Google Maps API Key:
   ```kotlin
   apiKey = "YOUR_API_KEY_HERE"
   ```
   *Note: For production, it is recommended to store this in `local.properties` or use Secrets Gradle Plugin.*

3. **Build and Run:**
   Sync the project with Gradle files and run the `app` module on an emulator or physical device.

## Project Structure

- `compose/`: Contains the UI components and map screens.
- `network/`: Retrofit instances and API service definitions.
- `models/`: Data classes for API responses and internal models.
- `di/`: Hilt modules for dependency injection.
- `Repository.kt`: Handles data fetching logic.
- `GoogleMapViewModel.kt`: Manages UI state and business logic.

## Screenshots

<img width="717" height="1600" alt="googleMapScreenShot" src="https://github.com/user-attachments/assets/4fbeef45-006a-493e-be6f-76991a07b6d5" />


## License

```text
Copyright 2026 Prem

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
