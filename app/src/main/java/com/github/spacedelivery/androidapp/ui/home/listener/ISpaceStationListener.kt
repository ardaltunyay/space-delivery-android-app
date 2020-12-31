package com.github.spacedelivery.androidapp.ui.home.listener

import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel

interface ISpaceStationListener {
    fun onTravelClicked(spaceStationUIModel: SpaceStationUIModel)
    fun onFavoriteClicked(spaceStationUIModel: SpaceStationUIModel)
}