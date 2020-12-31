package com.github.spacedelivery.androidapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "space_station")
data class SpaceStationEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "coordinate_x") val coordinateX: Int,
    @ColumnInfo(name = "coordinate_y") val coordinateY: Int,
    @ColumnInfo(name = "capacity") val capacity: Int,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "need") val need: Int
)