package com.github.spacedelivery.androidapp.ui.favourite_stations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.spacedelivery.androidapp.core.base.BaseViewModel
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain
import com.github.spacedelivery.androidapp.domain.usecase.SpaceStationUseCase
import com.github.spacedelivery.androidapp.ui.home.mapper.toUIModel
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel
import kotlinx.coroutines.launch

class FavouriteStationsViewModel(private val spaceStationUseCase: SpaceStationUseCase) :
    BaseViewModel() {

    private val favoriteSpaceStationsDomain = MutableLiveData<List<SpaceStationDomain>>()

    val favoriteSpaceStations = favoriteSpaceStationsDomain.map { domainList ->
        domainList.map { it.toUIModel(null) }
    }

    val isEmpty = favoriteSpaceStations.map {
        it.isNullOrEmpty()
    }

    init {
        fetchFavoriteSpaceStations()
    }

    fun fetchFavoriteSpaceStations() {
        viewModelScope.launch {
            spaceStationUseCase.fetchFavoriteSpaceStations().onSuccess { stationList ->
                favoriteSpaceStationsDomain.value = stationList
            }
        }
    }

    fun toggleFavoriteSpaceStation(spaceStationUIModel: SpaceStationUIModel) {
        viewModelScope.launch {
            val station =
                favoriteSpaceStationsDomain.value?.find { it.name == spaceStationUIModel.name }
            station?.let {
                spaceStationUseCase.toggleFavoriteSpaceStation(it)
                fetchFavoriteSpaceStations()
            }
        }
    }
}