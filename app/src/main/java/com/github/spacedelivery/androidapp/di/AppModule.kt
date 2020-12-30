package com.github.spacedelivery.androidapp.di

import androidx.room.Room
import com.github.spacedelivery.androidapp.data.local.AppDatabase
import com.github.spacedelivery.androidapp.data.local.createSpaceVehicleDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "SpaceDelivery-Db").build()
    }
    single { createSpaceVehicleDao(appDatabase = get()) }

}