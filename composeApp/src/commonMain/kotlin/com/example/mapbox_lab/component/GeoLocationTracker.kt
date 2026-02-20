package com.example.mapbox_lab.component

import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.PermissionsController

expect fun getLocationTracker(permissionsController: PermissionsController) : LocationTracker