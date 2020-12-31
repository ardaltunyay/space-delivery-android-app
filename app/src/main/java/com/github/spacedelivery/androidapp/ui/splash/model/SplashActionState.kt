package com.github.spacedelivery.androidapp.ui.splash.model

sealed class SplashActionState {
    object NoHaveSpaceVehicle : SplashActionState()
    object HasSpaceVehicle : SplashActionState()
}