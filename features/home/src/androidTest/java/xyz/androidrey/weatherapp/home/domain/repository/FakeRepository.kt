package xyz.androidrey.weatherapp.home.domain.repository

import xyz.androidrey.multimoduletemplate.network.NetworkResult
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData
import xyz.androidrey.weatherapp.home.domain.util.mockCurrentData

class FakeRepository : CurrentRepository {
    override suspend fun getCurrentData(query: String): NetworkResult<CurrentData> {
        return NetworkResult.Success(
            mockCurrentData
        )
    }
}