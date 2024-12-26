package xyz.androidrey.weatherapp.home.domain.repository

import xyz.androidrey.multimoduletemplate.network.NetworkResult
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData

interface CurrentRepository {

    suspend fun getCurrentData(query: String): NetworkResult<CurrentData>

}