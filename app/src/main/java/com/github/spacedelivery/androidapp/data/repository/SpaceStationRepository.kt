package com.github.spacedelivery.androidapp.data.repository

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.data.local.dao.ISpaceStationDao
import com.github.spacedelivery.androidapp.data.local.preferences.LocalPreferences
import com.github.spacedelivery.androidapp.data.remote.SpaceStationRemoteDataSource
import com.github.spacedelivery.androidapp.domain.mapper.toDomain
import com.github.spacedelivery.androidapp.domain.mapper.toEntity
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain

class SpaceStationRepository(
    private val localPref: LocalPreferences,
    private val remoteDataSource: SpaceStationRemoteDataSource,
    private val spaceStationDao: ISpaceStationDao
) {

    suspend fun updateCurrentSpaceStation(spaceStationDomain: SpaceStationDomain?) {
        localPref.currentSpaceStation = spaceStationDomain
    }

    suspend fun fetchCurrentSpacStation(): SpaceStationDomain? {
        return localPref.currentSpaceStation
    }

    suspend fun fetchRemoteSpaceStations(): Result<List<SpaceStationDomain>> {
        return when (val result = remoteDataSource.getSpaceStations()) {
            is Result.Success -> Result.Success(result.data?.map { it.toDomain() })
            is Result.Error -> Result.Error(result.exception)
        }
    }

    suspend fun fetLocalSpaceStations(): List<SpaceStationDomain>? {
        return spaceStationDao.getAllSpaceStations()?.map { it.toDomain() }
    }

    suspend fun fetchFavoriteSpaceStations(): List<SpaceStationDomain>? {
        return spaceStationDao.getFavoriteSpaceStations()?.map { it.toDomain() }
    }

    suspend fun updateSpaceStation(spaceStationDomain: SpaceStationDomain) {
        spaceStationDao.update(spaceStationDomain.toEntity())
    }

    suspend fun addSpaceStation(spaceStationDomain: SpaceStationDomain) {
        spaceStationDao.insert(spaceStationDomain.toEntity())
    }

    suspend fun getSpaceStation(name: String): SpaceStationDomain? {
        return spaceStationDao.get(name)?.toDomain()
    }

}