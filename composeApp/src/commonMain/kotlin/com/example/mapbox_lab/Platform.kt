package com.example.mapbox_lab

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform