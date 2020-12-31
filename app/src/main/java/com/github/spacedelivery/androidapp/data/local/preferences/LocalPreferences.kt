package com.github.spacedelivery.androidapp.data.local.preferences

import android.content.SharedPreferences
import com.github.spacedelivery.androidapp.core.preferences.get
import com.github.spacedelivery.androidapp.core.preferences.set
import com.github.spacedelivery.androidapp.domain.model.CurrentPropertiesDomain
import com.github.spacedelivery.androidapp.domain.model.SpaceStationDomain

private const val CURRENT_SPACE_STATION = "CURRENT_SPACE_STATION"
private const val CURRENT_PROPERTIES = "CURRENT_PROPERTIES"


class LocalPreferences(private val sharedPref: SharedPreferences) {

    var currentSpaceStation: SpaceStationDomain?
        get() = sharedPref[CURRENT_SPACE_STATION, null]
        set(value) {
            sharedPref[CURRENT_SPACE_STATION] = value
        }

    var currentProperties: CurrentPropertiesDomain?
        get() = sharedPref[CURRENT_PROPERTIES, null]
        set(value) {
            sharedPref[CURRENT_PROPERTIES] = value
        }

}