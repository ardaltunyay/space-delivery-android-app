package com.github.spacedelivery.androidapp.domain.usecase

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.data.repository.SpaceVehicleRepository
import com.github.spacedelivery.androidapp.domain.mapper.toEntity
import com.github.spacedelivery.androidapp.domain.model.SpaceVehicleDomain

class SpaceVehicleUseCase(private val spaceVehicleRepository: SpaceVehicleRepository) {

    suspend fun createSpaceVehicle(
        name: String,
        strength: Int,
        speed: Int,
        materialCapacity: Int
    ): Result<SpaceVehicleDomain> {

        val domainModel = SpaceVehicleDomain(
            id = 1,
            name = name,
            strength = strength,
            speed = speed,
            materialCapacity = materialCapacity,
            damageCapacity = 100
        )

        spaceVehicleRepository.create(domainModel.toEntity())

        val spaceVehicle = spaceVehicleRepository.get(1)

        return spaceVehicle?.let { Result.Success(it) } ?: Result.Error(Exception())
    }

    suspend fun getSpaceVehicle(): Result<SpaceVehicleDomain> {

        val spaceVehicle = spaceVehicleRepository.get(1)

        return spaceVehicle?.let { Result.Success(it) } ?: Result.Error(Exception())
    }

}