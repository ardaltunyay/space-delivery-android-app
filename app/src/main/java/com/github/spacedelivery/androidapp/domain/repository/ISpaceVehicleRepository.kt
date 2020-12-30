package com.github.spacedelivery.androidapp.domain.repository

import com.github.spacedelivery.androidapp.data.local.entity.SpaceVehicleEntity
import com.github.spacedelivery.androidapp.domain.model.SpaceVehicleDomain

interface ISpaceVehicleRepository {
    suspend fun create(spaceVehicleEntity: SpaceVehicleEntity)
    suspend fun get(id: Int): SpaceVehicleDomain?
}