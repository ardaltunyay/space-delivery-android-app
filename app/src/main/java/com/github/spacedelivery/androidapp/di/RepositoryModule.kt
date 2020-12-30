package com.github.spacedelivery.androidapp.di

import com.github.spacedelivery.androidapp.data.repository.SpaceVehicleRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SpaceVehicleRepository(spaceVehicleDao = get()) }
}