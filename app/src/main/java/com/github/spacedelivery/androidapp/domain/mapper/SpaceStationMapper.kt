package com.github.spacedelivery.androidapp.domain.mapper

import com.github.spacedelivery.androidapp.data.local.entity.SpaceStationEntity
import com.github.spacedelivery.androidapp.data.remote.model.SpaceStationResponse
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain

fun SpaceStationResponse.toDomain(): SpaceStationDomain =
    SpaceStationDomain(
        name = name,
        coordinateX = coordinateX,
        coordinateY = coordinateY,
        capacity = capacity,
        stock = stock,
        need = need,
        isFavorite = false,
        isActive = true
    )

fun SpaceStationEntity.toDomain(): SpaceStationDomain =
    SpaceStationDomain(
        name = name,
        coordinateX = coordinateX,
        coordinateY = coordinateY,
        capacity = capacity,
        stock = stock,
        need = need,
        isFavorite = isFavorite,
        isActive = isActive
    )

fun SpaceStationDomain.toEntity(): SpaceStationEntity =
    SpaceStationEntity(
        name = name,
        coordinateX = coordinateX,
        coordinateY = coordinateY,
        capacity = capacity,
        stock = stock,
        need = need,
        isFavorite = isFavorite,
        isActive = isActive
    )