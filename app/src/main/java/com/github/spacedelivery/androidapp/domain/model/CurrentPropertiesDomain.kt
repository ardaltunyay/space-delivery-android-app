package com.github.spacedelivery.androidapp.domain.model

data class CurrentPropertiesDomain(
    val UGS: Int,
    val EUS: Int,
    val DS: Int
) {
    fun damageAfterSeconds(): Int = DS / 1000
}