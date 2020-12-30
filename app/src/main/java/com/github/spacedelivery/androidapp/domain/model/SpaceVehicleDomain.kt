package com.github.spacedelivery.androidapp.domain.model

data class SpaceVehicleDomain(
    val id: Int,
    val name: String,
    val damageCapacity: Int,
    val speed: Int,
    val strength: Int,
    val materialCapacity: Int
)