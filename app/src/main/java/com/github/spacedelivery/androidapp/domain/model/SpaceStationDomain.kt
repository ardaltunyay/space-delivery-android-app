package com.github.spacedelivery.androidapp.domain.model

import kotlin.math.pow
import kotlin.math.sqrt


data class SpaceStationDomain(
    val name: String,
    val coordinateX: Int,
    val coordinateY: Int,
    val capacity: Int,
    val stock: Int,
    val need: Int,
    val isFavorite: Boolean
) {

    fun getDistanceFrom(x: Int, y: Int): Int {
        val distance = sqrt(
            (x - coordinateX).toDouble().pow(2.0) + (y - coordinateY).toDouble().pow(2.0)
        )
        return distance.toInt()
    }
}