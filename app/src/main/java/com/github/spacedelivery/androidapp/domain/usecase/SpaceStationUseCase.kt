package com.github.spacedelivery.androidapp.domain.usecase

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.data.repository.CurrentPropertiesRepository
import com.github.spacedelivery.androidapp.data.repository.SpaceStationRepository
import com.github.spacedelivery.androidapp.domain.model.CurrentPropertiesDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain

class SpaceStationUseCase(
    private val spaceStationRepository: SpaceStationRepository) {

    suspend fun updateCurrentSpaceStation(spaceStationDomain: SpaceStationDomain?) {
        spaceStationRepository.updateCurrentSpaceStation(spaceStationDomain)
    }

    suspend fun fetchCurrentSpacStation(): SpaceStationDomain? {
        return spaceStationRepository.fetchCurrentSpacStation()
    }

    suspend fun fetchSpaceStations(): Result<List<SpaceStationDomain>> {
        spaceStationRepository.fetchCurrentSpacStation()
        return spaceStationRepository.fetchSpaceStations()
    }
}