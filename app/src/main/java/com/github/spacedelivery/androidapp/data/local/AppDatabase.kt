package com.github.spacedelivery.androidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.spacedelivery.androidapp.data.local.dao.ISpaceStationDao
import com.github.spacedelivery.androidapp.data.local.dao.ISpaceVehicleDao
import com.github.spacedelivery.androidapp.data.local.entity.SpaceStationEntity
import com.github.spacedelivery.androidapp.data.local.entity.SpaceVehicleEntity

@Database(entities = [SpaceVehicleEntity::class, SpaceStationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spaceVehicleDao(): ISpaceVehicleDao
    abstract fun spaceStationDao(): ISpaceStationDao
}