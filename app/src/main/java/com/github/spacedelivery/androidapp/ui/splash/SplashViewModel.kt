package com.github.spacedelivery.androidapp.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.spacedelivery.androidapp.core.base.BaseViewModel
import com.github.spacedelivery.androidapp.domain.usecase.SpaceVehicleUseCase
import com.github.spacedelivery.androidapp.ui.splash.model.SplashActionState
import kotlinx.coroutines.launch

class SplashViewModel(private val spaceVehicleUseCase: SpaceVehicleUseCase) : BaseViewModel() {

    val actionState = MutableLiveData<SplashActionState>()

    init {
        checkSpaceVehicle()
    }

    private fun checkSpaceVehicle() {
        viewModelScope.launch {
            spaceVehicleUseCase.getSpaceVehicle()
                .onSuccess {
                    actionState.value = SplashActionState.HasSpaceVehicle
                }.onError {
                    actionState.value = SplashActionState.NoHaveSpaceVehicle
                }
        }
    }

}