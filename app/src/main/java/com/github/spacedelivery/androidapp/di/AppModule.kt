package com.github.spacedelivery.androidapp.di

import android.content.Context
import androidx.room.Room
import com.github.spacedelivery.androidapp.data.local.AppDatabase
import com.github.spacedelivery.androidapp.data.local.createSpaceVehicleDao
import com.github.spacedelivery.androidapp.data.local.preferences.LocalPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { androidContext().getSharedPreferences("LocalPrefs", Context.MODE_PRIVATE) }
    single { LocalPreferences(sharedPref = get()) }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "SpaceDelivery-Db").build()
    }
    single { createSpaceVehicleDao(appDatabase = get()) }

}