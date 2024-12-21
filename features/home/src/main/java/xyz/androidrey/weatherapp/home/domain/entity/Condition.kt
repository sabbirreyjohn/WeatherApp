package xyz.androidrey.weatherapp.home.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)