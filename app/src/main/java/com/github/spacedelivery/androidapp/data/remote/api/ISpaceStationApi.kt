package com.github.spacedelivery.androidapp.data.remote.api

import com.github.spacedelivery.androidapp.data.remote.model.SpaceStationResponse
import retrofit2.Response
import retrofit2.http.GET

interface ISpaceStationApi {

    @GET("e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getSpaceStations(): Response<SpaceStationResponse>
}