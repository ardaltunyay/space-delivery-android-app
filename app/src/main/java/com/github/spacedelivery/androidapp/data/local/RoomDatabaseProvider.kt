package com.github.spacedelivery.androidapp.data.local

import com.github.spacedelivery.androidapp.data.local.dao.ISpaceStationDao
import com.github.spacedelivery.androidapp.data.local.dao.ISpaceVehicleDao

fun createSpaceVehicleDao(appDatabase: AppDatabase): ISpaceVehicleDao =
    appDatabase.spaceVehicleDao()

fun createSpaceStationeDao(appDatabase: AppDatabase): ISpaceStationDao =
    appDatabase.spaceStationDao()