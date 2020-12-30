package com.github.spacedelivery.androidapp.domain.mapper

import com.github.spacedelivery.androidapp.data.local.entity.SpaceVehicleEntity
import com.github.spacedelivery.androidapp.domain.model.SpaceVehicleDomain

fun SpaceVehicleDomain.toEntity(): SpaceVehicleEntity =
    SpaceVehicleEntity(
        id = id,
        name = name,
        damageCapacity = 100,
        speed = speed,
        strength = strength,
        materialCapacity = materialCapacity
    )

fun SpaceVehicleEntity.toDomain(): SpaceVehicleDomain =
    SpaceVehicleDomain(
        id = id,
        name = name,
        damageCapacity = damageCapacity,
        speed = speed,
        strength = strength,
        materialCapacity = materialCapacity
    )