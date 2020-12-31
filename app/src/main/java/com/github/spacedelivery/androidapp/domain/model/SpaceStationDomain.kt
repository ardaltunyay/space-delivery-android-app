package com.github.spacedelivery.androidapp.domain.model

data class SpaceStationDomain(
    val name: String,
    val coordinateX: Int,
    val coordinateY: Int,
    val capacity: Int,
    val stock: Int,
    val need: Int
)