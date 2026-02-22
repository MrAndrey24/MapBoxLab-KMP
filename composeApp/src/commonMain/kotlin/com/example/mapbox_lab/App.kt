package com.example.mapbox_lab

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mapbox_lab.component.MapViewComponent
import com.example.mapbox_lab.component.PermissionsRequester
import com.example.mapbox_lab.component.getLocationTracker
import com.example.mapbox_lab.model.PermissionRequest
import com.example.shared.presentation.LocationViewModel
import dev.icerock.moko.geo.compose.BindLocationTrackerEffect
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.location.LOCATION
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<LocationViewModel>()
    AppContent(viewModel = viewModel)
}

@Composable
fun AppContent(viewModel: LocationViewModel) {
    val coordinate by viewModel.coordinate.collectAsState()

    MaterialTheme {
        Scaffold { innerPadding ->
            PermissionsRequester(
                permissions = listOf(
                    PermissionRequest(
                        permission = Permission.LOCATION,
                        title = "Location",
                        description = "To show user location",
                        icon = Icons.Default.LocationOn
                    )
                ),
                onAllPermissionsGranted = {
                    println("All permissions granted!")
                }
            ) { controller ->

                val locationTracker = remember(controller) {
                    getLocationTracker(controller)
                }

                BindLocationTrackerEffect(locationTracker = locationTracker)

                LaunchedEffect(locationTracker) {
                    println("Starting location tracking...")
                    viewModel.startTracking(locationTracker)
                }

                DisposableEffect(locationTracker) {
                    onDispose {
                        println("Stopping location tracking...")
                        viewModel.stopTracking(locationTracker)
                    }
                }

                MapViewComponent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    coordinate = coordinate
                )
            }
        }
    }
}