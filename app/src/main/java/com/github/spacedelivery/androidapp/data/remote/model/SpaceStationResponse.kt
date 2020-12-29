package com.github.spacedelivery.androidapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class SpaceStationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("coordinateX")
    val coordinateX: Int,
    @SerializedName("coordinateY")
    val coordinateY: Int,
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("need")
    val need: Int
)