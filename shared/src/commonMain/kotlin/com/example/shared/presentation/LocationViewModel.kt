package com.example.shared.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.entities.Coordinate
import com.example.shared.domain.usecases.StopTrackingUseCase
import com.example.shared.domain.usecases.TrackUserLocationUseCase
import dev.icerock.moko.geo.LocationTracker
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewModel(private val trackLocationUseCase: TrackUserLocationUseCase, private val stopTrackingUseCase: StopTrackingUseCase) : ViewModel() {

//    Default coordinate
    private val _coordinate = MutableStateFlow(Coordinate(9.9895775, -84.8931864))
    val coordinate: StateFlow<Coordinate> = _coordinate.asStateFlow()
    private var trackingJob: Job? = null

    fun startTracking(tracker: LocationTracker) {
        trackingJob?.cancel()
        trackingJob = viewModelScope.launch {
            trackLocationUseCase(tracker).collect { coordinate ->
                _coordinate.value = coordinate
            }
        }
    }

    fun stopTracking(tracker: LocationTracker) {
        trackingJob?.cancel()
        trackingJob = null
        stopTrackingUseCase(tracker)
    }
}