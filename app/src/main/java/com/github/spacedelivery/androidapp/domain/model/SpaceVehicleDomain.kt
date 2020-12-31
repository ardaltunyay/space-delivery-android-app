package com.github.spacedelivery.androidapp.domain.model

data class SpaceVehicleDomain(
    val id: Int,
    val name: String,
    val damageCapacity: Int,
    val speed: Int,
    val strength: Int,
    val materialCapacity: Int
) {
    fun getUGS(): Int = materialCapacity * 10000

    fun getEUS(): Int = speed * 20

    fun getDS(): Int = strength * 10000
}