package com.github.spacedelivery.androidapp.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.spacedelivery.androidapp.core.base.BaseViewModel
import com.github.spacedelivery.androidapp.data.repository.CurrentPropertiesRepository
import com.github.spacedelivery.androidapp.domain.model.CurrentPropertiesDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceVehicleDomain
import com.github.spacedelivery.androidapp.domain.usecase.SpaceStationUseCase
import com.github.spacedelivery.androidapp.domain.usecase.SpaceVehicleUseCase
import com.github.spacedelivery.androidapp.ui.home.mapper.toUIModel
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val spaceStationUseCase: SpaceStationUseCase,
    private val spaceVehicleUseCase: SpaceVehicleUseCase,
    private val currentPropertiesRepository: CurrentPropertiesRepository
) : BaseViewModel() {

    val queryText = MutableLiveData("")

    val currentProperties = MutableLiveData<CurrentPropertiesDomain>()

    val currentSpaceStation = MutableLiveData<SpaceStationDomain>()

    val spaceVehicle = MutableLiveData<SpaceVehicleDomain>()

    private val spaceStationList = MutableLiveData<List<SpaceStationUIModel>>()

    private val filteredSpaceStationList = queryText.map { getFilteredSpaceStationList(it) }

    private fun getFilteredSpaceStationList(searchText: String): List<SpaceStationUIModel> {
        val list = spaceStationList.value ?: emptyList()
        return list.filter { it.name.contains(searchText, true) }
    }

    val spaceStationListCombination = MediatorLiveData<List<SpaceStationUIModel>>().apply {
        addSource(spaceStationList) {
            value = it
        }
        addSource(filteredSpaceStationList) {
            value = it
        }
    }

    init {
        fetchCurrentProperties()
        fetchSpaceStations()
        fetchSpaceVehicle()
    }

    private fun fetchCurrentProperties() {
        viewModelScope.launch {
            currentProperties.value = currentPropertiesRepository.fetchCurrentProperties()
        }
    }

    private fun fetchSpaceVehicle() {
        viewModelScope.launch {
            spaceVehicle.value = spaceVehicleUseCase.getSpaceVehicle().getOrNull()
        }
    }

    private fun fetchSpaceStations() {
        viewModelScope.launch {

            val currentStation = spaceStationUseCase.fetchCurrentSpacStation()
            currentSpaceStation.value = currentStation

            spaceStationUseCase.fetchSpaceStations().onSuccess { listDomain ->
                spaceStationList.value = listDomain?.map { it.toUIModel(currentStation) }
            }
        }
    }

}