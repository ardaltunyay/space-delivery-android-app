package com.github.spacedelivery.androidapp.data.repository

import com.github.spacedelivery.androidapp.data.local.preferences.LocalPreferences
import com.github.spacedelivery.androidapp.domain.model.CurrentPropertiesDomain

class CurrentPropertiesRepository(private val localPref: LocalPreferences) {

    fun updateCurrentProperties(currentPropertiesDomain: CurrentPropertiesDomain) {
        localPref.currentProperties = currentPropertiesDomain
    }

    fun fetchCurrentProperties(): CurrentPropertiesDomain? {
        return localPref.currentProperties
    }
}