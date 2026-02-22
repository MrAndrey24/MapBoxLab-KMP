package com.example.shared.data.local

import com.example.shared.data.repository.ILocationRepository
import com.example.shared.domain.entities.Coordinate
import dev.icerock.moko.geo.LocationTracker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationLocalDataSource : ILocationRepository {
    override fun getLocationUpdates(tracker: LocationTracker): Flow<Coordinate> = flow {
        tracker.startTracking()

        tracker.getLocationsFlow().collect {
            it ->
            emit(Coordinate(
                it.latitude,
                it.longitude
            ))
        }
    }

    override fun stopTracking(tracker: LocationTracker) {
        tracker.stopTracking()
    }
}