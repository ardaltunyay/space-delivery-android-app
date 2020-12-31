package com.github.spacedelivery.androidapp.domain.usecase

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.data.repository.CurrentPropertiesRepository
import com.github.spacedelivery.androidapp.data.repository.SpaceStationRepository
import com.github.spacedelivery.androidapp.data.repository.SpaceVehicleRepository
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain

class SpaceStationUseCase(
    private val spaceStationRepository: SpaceStationRepository,
    private val currentPropertiesRepository: CurrentPropertiesRepository,
    private val spaceVehicleRepository: SpaceVehicleRepository
) {

    suspend fun addSpaceStation(spaceStationDomain: SpaceStationDomain) {
        spaceStationRepository.addSpaceStation(spaceStationDomain)
    }

    suspend fun updateCurrentSpaceStation(spaceStationDomain: SpaceStationDomain?) {
        spaceStationRepository.updateCurrentSpaceStation(spaceStationDomain)
    }

    suspend fun fetchCurrentSpacStation(): SpaceStationDomain? {
        return spaceStationRepository.fetchCurrentSpacStation()
    }

    suspend fun fetchFavoriteSpaceStations(): Result<List<SpaceStationDomain>> {
        val result = spaceStationRepository.fetchFavoriteSpaceStations()
        return Result.Success(result)
    }

    suspend fun fetchSpaceStations(): Result<List<SpaceStationDomain>> {

        val remoteList =
            spaceStationRepository.fetchRemoteSpaceStations().getOrNull() ?: emptyList()
        val localList = spaceStationRepository.fetLocalSpaceStations() ?: emptyList()
        val currentSpaceStation = spaceStationRepository.fetchCurrentSpacStation()

        val list = mergeRemoteAndLocalSpacesStationList(
            currentSpaceStation = currentSpaceStation,
            remoteList = remoteList.toMutableList(),
            localList = localList.toMutableList()
        )

        return Result.Success(list)
    }

    private fun mergeRemoteAndLocalSpacesStationList(
        currentSpaceStation: SpaceStationDomain?,
        remoteList: MutableList<SpaceStationDomain>,
        localList: MutableList<SpaceStationDomain>
    ): List<SpaceStationDomain> {

        localList.forEach { local ->
            val station = remoteList.find { it.name == local.name }
            val index = remoteList.indexOf(station)
            remoteList.removeAt(index)
            remoteList.add(index, local)
        }

        if (currentSpaceStation != null) {
            val spaceStation = remoteList.find { it.name == currentSpaceStation.name }
            remoteList.remove(spaceStation)
        }

        return remoteList.toList()
    }

    suspend fun toggleFavoriteSpaceStation(spaceStationDomain: SpaceStationDomain) {
        val local = spaceStationRepository.getSpaceStation(spaceStationDomain.name)
        if (local == null) {
            spaceStationRepository.addSpaceStation(spaceStationDomain.copy(isFavorite = !spaceStationDomain.isFavorite))
        } else {
            spaceStationRepository.updateSpaceStation(spaceStationDomain.copy(isFavorite = !spaceStationDomain.isFavorite))
        }

    }

    suspend fun getTravelToSpaceStation(targetStation: SpaceStationDomain): Result<Boolean> {
        try {
            val world = SpaceStationDomain(
                name = "Dünya",
                coordinateX = 0,
                coordinateY = 0,
                capacity = 0,
                stock = 0,
                need = 0,
                isFavorite = false,
                isActive = false
            )

            val currentSation = spaceStationRepository.fetchCurrentSpacStation()!!
            val currentProperties = currentPropertiesRepository.fetchCurrentProperties()!!
            val spaceVehicle = spaceVehicleRepository.get(1)!!

            val spendEUS =
                currentSation.getDistanceFrom(targetStation.coordinateX, targetStation.coordinateY)

            //Check conditions
            val result = when {
                currentProperties.UGS == 0 -> Result.Error(Exception("There are not UGS!"))
                currentProperties.EUS < spendEUS -> Result.Error(Exception("There are not EUS or not enough!"))
                spaceVehicle.damageCapacity == 0 -> Result.Error(Exception("There are not damage capacity!"))
                else -> null
            }

            if (result != null) {
                spaceStationRepository.updateCurrentSpaceStation(world)
                return result
            }

            //Values
            val newUGS =
                if (currentProperties.UGS < targetStation.need) 0 else currentProperties.UGS - targetStation.need

            val newEUS = currentProperties.EUS - spendEUS
            val newStock = targetStation.stock + (currentProperties.UGS - newUGS)
            val newNeed = targetStation.capacity - newStock

            //Save values
            val newProperties = currentProperties.copy(UGS = newUGS, EUS = newEUS)
            val updatedTargetStation =
                targetStation.copy(isActive = false, need = newNeed, stock = newStock)

            spaceStationRepository.addSpaceStation(updatedTargetStation)
            spaceStationRepository.updateCurrentSpaceStation(updatedTargetStation)
            currentPropertiesRepository.updateCurrentProperties(newProperties)

            if (newProperties.UGS == 0) {
                spaceStationRepository.updateCurrentSpaceStation(world)
                return Result.Success(true)
            }
            return Result.Success(false)

        } catch (ex: Exception) {
            return Result.Error(Exception())
        }
    }
}