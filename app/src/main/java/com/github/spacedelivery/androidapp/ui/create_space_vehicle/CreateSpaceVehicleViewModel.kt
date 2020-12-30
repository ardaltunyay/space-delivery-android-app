package com.github.spacedelivery.androidapp.ui.create_space_vehicle

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.spacedelivery.androidapp.core.base.BaseViewModel
import com.github.spacedelivery.androidapp.domain.usecase.SpaceVehicleUseCase
import com.github.spacedelivery.androidapp.ui.create_space_vehicle.model.CreateSpaceVehicleActionState
import com.github.spacedelivery.androidapp.ui.create_space_vehicle.model.PropertyUIModel
import kotlinx.coroutines.launch

class CreateSpaceVehicleViewModel(private val spaceVehicleUseCase: SpaceVehicleUseCase) :
    BaseViewModel() {

    val actionState = MutableLiveData<CreateSpaceVehicleActionState>()

    val remainingPoints = MutableLiveData(15)

    val name = MutableLiveData("")
    val strength = MutableLiveData(PropertyUIModel(1, 13))
    val speed = MutableLiveData(PropertyUIModel(1, 13))
    val capacity = MutableLiveData(PropertyUIModel(1, 13))

    val buttonStatus = MediatorLiveData<Boolean>().apply {
        addSource(remainingPoints) {
            value = checkState()
        }
        addSource(name) {
            value = checkState()
        }
    }

    private fun checkState(): Boolean {
        return when {
            name.value.isNullOrEmpty() -> false
            remainingPoints.value != 0 -> false
            else -> true
        }
    }

    private fun updateProperties() {
        val usedPoints = getUsedPoints()

        val newRemainingPoints = 15 - usedPoints
        remainingPoints.value = newRemainingPoints

        strength.value = strength.value?.let {
            it.copy(maxValue = it.value + newRemainingPoints)
        }

        speed.value = speed.value?.let {
            it.copy(maxValue = it.value + newRemainingPoints)
        }

        capacity.value = capacity.value?.let {
            it.copy(maxValue = it.value + newRemainingPoints)
        }


    }

    fun updateStrength(value: Int) {
        val newValue = if (value == 0) 1 else value
        strength.value = strength.value?.copy(value = newValue)
        updateProperties()
    }

    fun updateSpeed(value: Int) {
        val newValue = if (value == 0) 1 else value
        speed.value = speed.value?.copy(value = newValue)
        updateProperties()
    }

    fun updateCapacity(value: Int) {
        val newValue = if (value == 0) 1 else value
        capacity.value = capacity.value?.copy(value = newValue)
        updateProperties()
    }

    private fun getUsedPoints(): Int {
        val strength = strength.value?.value ?: 0
        val speed = speed.value?.value ?: 0
        val capacity = capacity.value?.value ?: 0
        return strength + speed + capacity
    }

    fun createSpaceVehicle() {
        viewModelScope.launch {
            try {
                checkErrorAndContinue {
                    spaceVehicleUseCase.createSpaceVehicle(
                        name = name.value!!,
                        strength = strength.value!!.value,
                        speed = speed.value!!.value,
                        materialCapacity = capacity.value!!.value
                    )
                }.onSuccess {
                    actionState.value = CreateSpaceVehicleActionState.CreatedSpaceVehicleState
                }
            } catch (ex: Exception) {

            }

        }
    }

}