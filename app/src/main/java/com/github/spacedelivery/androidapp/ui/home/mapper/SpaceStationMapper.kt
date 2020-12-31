package com.github.spacedelivery.androidapp.ui.home.mapper

import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel

fun SpaceStationDomain.toUIModel(currentStation: SpaceStationDomain?): SpaceStationUIModel =
    SpaceStationUIModel(
        name = name,
        capacity = "$stock/$capacity",
        eus = "${
            this.getDistanceFrom(
                currentStation?.coordinateX ?: 0,
                currentStation?.coordinateY ?: 0
            )
        } EUS",
        isFavorite = isFavorite,
        isActive = isActive
    )