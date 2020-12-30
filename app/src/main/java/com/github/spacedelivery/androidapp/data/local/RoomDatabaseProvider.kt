package com.github.spacedelivery.androidapp.data.local

import com.github.spacedelivery.androidapp.data.local.dao.SpaceVehicleDao

fun createSpaceVehicleDao(appDatabase: AppDatabase): SpaceVehicleDao = appDatabase.spaceVehicleDao()