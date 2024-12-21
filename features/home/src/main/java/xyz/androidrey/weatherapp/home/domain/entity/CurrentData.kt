package xyz.androidrey.weatherapp.home.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class CurrentData(
    val current: Current,
    val location: Location
)