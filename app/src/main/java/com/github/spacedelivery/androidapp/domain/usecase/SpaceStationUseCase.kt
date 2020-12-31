package com.github.spacedelivery.androidapp.domain.usecase

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.data.repository.SpaceStationRepository
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain

class SpaceStationUseCase(
    private val spaceStationRepository: SpaceStationRepository
) {

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
}