package com.example.shared.domain.usecases

import com.example.shared.data.repository.ILocationRepository
import dev.icerock.moko.geo.LocationTracker

class StopTrackingUseCase(private val repository: ILocationRepository) {

    operator fun invoke(tracker: LocationTracker) {
        repository.stopTracking(tracker)
    }
}