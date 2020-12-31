package com.github.spacedelivery.androidapp.di

import com.github.spacedelivery.androidapp.data.repository.CurrentPropertiesRepository
import com.github.spacedelivery.androidapp.data.repository.SpaceStationRepository
import com.github.spacedelivery.androidapp.data.repository.SpaceVehicleRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SpaceVehicleRepository(spaceVehicleDao = get()) }
    single { SpaceStationRepository(remoteDataSource = get(), localPref = get()) }
    single { CurrentPropertiesRepository(localPref = get()) }
}