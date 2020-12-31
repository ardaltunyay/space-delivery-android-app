package com.github.spacedelivery.androidapp.data.repository

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.data.local.preferences.LocalPreferences
import com.github.spacedelivery.androidapp.data.remote.SpaceStationRemoteDataSource
import com.github.spacedelivery.androidapp.domain.mapper.toDomain
import com.github.spacedelivery.androidapp.domain.model.CurrentPropertiesDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain

class SpaceStationRepository(
    private val localPref: LocalPreferences,
    private val remoteDataSource: SpaceStationRemoteDataSource
) {

    suspend fun updateCurrentSpaceStation(spaceStationDomain: SpaceStationDomain?) {
        localPref.currentSpaceStation = spaceStationDomain
    }

    suspend fun fetchCurrentSpacStation(): SpaceStationDomain? {
        return localPref.currentSpaceStation
    }

    suspend fun fetchSpaceStations(): Result<List<SpaceStationDomain>> {
        return when (val result = remoteDataSource.getSpaceStations()) {
            is Result.Success -> Result.Success(result.data?.map { it.toDomain() })
            is Result.Error -> Result.Error(result.exception)
        }
    }
}