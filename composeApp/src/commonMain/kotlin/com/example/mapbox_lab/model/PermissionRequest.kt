package com.example.mapbox_lab.model

import androidx.compose.ui.graphics.vector.ImageVector
import dev.icerock.moko.permissions.Permission

data class PermissionRequest(
    val permission: Permission,
    val title: String,
    val description: String,
    val icon: ImageVector
)
