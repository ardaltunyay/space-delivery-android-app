package com.github.spacedelivery.androidapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "space_vehicle")
data class SpaceVehicleEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "damage_capacity") val damageCapacity: Int,
    @ColumnInfo(name = "speed") val speed: Int,
    @ColumnInfo(name = "strength") val strength: Int,
    @ColumnInfo(name = "material_capacity") val materialCapacity: Int
)