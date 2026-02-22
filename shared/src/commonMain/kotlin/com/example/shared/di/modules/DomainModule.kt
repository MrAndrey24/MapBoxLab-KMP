package com.example.shared.di.modules

import com.example.shared.domain.usecases.StopTrackingUseCase
import com.example.shared.domain.usecases.TrackUserLocationUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { TrackUserLocationUseCase(get()) }
    factory { StopTrackingUseCase(get()) }
}