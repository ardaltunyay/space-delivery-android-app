package com.github.spacedelivery.androidapp.data.remote

import com.github.spacedelivery.androidapp.core.network.Result
import com.github.spacedelivery.androidapp.core.network.safeApiCall
import com.github.spacedelivery.androidapp.data.remote.api.ISpaceStationApi
import com.github.spacedelivery.androidapp.data.remote.model.SpaceStationResponse

class SpaceStationRemoteDataSource(private val spaceStationApi: ISpaceStationApi) {

    suspend fun getSpaceStations(): Result<List<SpaceStationResponse>> =
        safeApiCall { spaceStationApi.getSpaceStations() }
}