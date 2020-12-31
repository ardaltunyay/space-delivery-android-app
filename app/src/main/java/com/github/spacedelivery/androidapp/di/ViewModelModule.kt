package com.github.spacedelivery.androidapp.di

import com.github.spacedelivery.androidapp.ui.create_space_vehicle.CreateSpaceVehicleViewModel
import com.github.spacedelivery.androidapp.ui.favourite_stations.FavouriteStationsViewModel
import com.github.spacedelivery.androidapp.ui.home.HomeViewModel
import com.github.spacedelivery.androidapp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(spaceVehicleUseCase = get()) }
    viewModel { CreateSpaceVehicleViewModel(spaceVehicleUseCase = get()) }
    viewModel {
        HomeViewModel(
            spaceStationUseCase = get(),
            spaceVehicleUseCase = get(),
            currentPropertiesRepository = get()
        )
    }
    viewModel { FavouriteStationsViewModel(spaceStationUseCase = get()) }
}