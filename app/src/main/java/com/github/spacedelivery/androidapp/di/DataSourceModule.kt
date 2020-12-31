package com.github.spacedelivery.androidapp.di

import com.github.spacedelivery.androidapp.data.remote.SpaceStationRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { SpaceStationRemoteDataSource(spaceStationApi = get()) }
}