package com.github.spacedelivery.androidapp.di

import com.github.spacedelivery.androidapp.domain.usecase.SpaceStationUseCase
import com.github.spacedelivery.androidapp.domain.usecase.SpaceVehicleUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        SpaceVehicleUseCase(
            spaceVehicleRepository = get(),
            spaceStationUseCase = get(),
            currentPropertiesRepository = get()
        )
    }
    single {
        SpaceStationUseCase(
            spaceStationRepository = get(),
            currentPropertiesRepository = get(),
            spaceVehicleRepository = get()
        )
    }
}