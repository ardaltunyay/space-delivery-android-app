package com.github.spacedelivery.androidapp.di

import com.github.spacedelivery.androidapp.domain.usecase.SpaceVehicleUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { SpaceVehicleUseCase(spaceVehicleRepository = get()) }
}