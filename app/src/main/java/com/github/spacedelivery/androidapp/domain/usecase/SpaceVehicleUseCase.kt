package com.github.spacedelivery.androidapp.domain.usecase

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.data.repository.CurrentPropertiesRepository
import com.github.spacedelivery.androidapp.data.repository.SpaceVehicleRepository
import com.github.spacedelivery.androidapp.domain.mapper.toEntity
import com.github.spacedelivery.androidapp.domain.model.CurrentPropertiesDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceVehicleDomain

class SpaceVehicleUseCase(
    private val spaceVehicleRepository: SpaceVehicleRepository,
    private val spaceStationUseCase: SpaceStationUseCase,
    private val currentPropertiesRepository: CurrentPropertiesRepository
) {

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

        val spaceStationDomain = SpaceStationDomain(
            name = "Dünya",
            coordinateX = 0,
            coordinateY = 0,
            capacity = 0,
            stock = 0,
            need = 0,
            isFavorite = false,
            isActive = false
        )

        val currentPropertiesDomain = CurrentPropertiesDomain(
            UGS = domainModel.getUGS(),
            EUS = domainModel.getEUS(),
            DS = domainModel.getDS()
        )

        spaceVehicleRepository.create(domainModel.toEntity())
        spaceStationUseCase.updateCurrentSpaceStation(spaceStationDomain)
        currentPropertiesRepository.updateCurrentProperties(currentPropertiesDomain)
        spaceStationUseCase.addSpaceStation(spaceStationDomain)

        val spaceVehicle = spaceVehicleRepository.get(1)

        return spaceVehicle?.let { Result.Success(it) } ?: Result.Error(Exception())
    }

    suspend fun getSpaceVehicle(): Result<SpaceVehicleDomain> {

        val spaceVehicle = spaceVehicleRepository.get(1)

        return spaceVehicle?.let { Result.Success(it) } ?: Result.Error(Exception())
    }

}