package com.github.spacedelivery.androidapp.data.repository

import com.github.spacedelivery.androidapp.data.local.dao.SpaceVehicleDao
import com.github.spacedelivery.androidapp.data.local.entity.SpaceVehicleEntity
import com.github.spacedelivery.androidapp.domain.mapper.toDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceVehicleDomain
import com.github.spacedelivery.androidapp.domain.repository.ISpaceVehicleRepository

class SpaceVehicleRepository(private val spaceVehicleDao: SpaceVehicleDao) :
    ISpaceVehicleRepository {

    override suspend fun create(spaceVehicleEntity: SpaceVehicleEntity) {
        spaceVehicleDao.insert(spaceVehicleEntity)
    }

    override suspend fun get(id: Int): SpaceVehicleDomain? {
        return spaceVehicleDao.get(id)?.toDomain()
    }


}