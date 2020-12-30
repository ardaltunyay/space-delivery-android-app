package com.github.spacedelivery.androidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.spacedelivery.androidapp.data.local.dao.SpaceVehicleDao
import com.github.spacedelivery.androidapp.data.local.entity.SpaceVehicleEntity

@Database(entities = [SpaceVehicleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spaceVehicleDao(): SpaceVehicleDao
}