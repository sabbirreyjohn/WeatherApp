package xyz.androidrey.weatherapp.home.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import live.studyquran.android.common.data.DataStoreRepository

class FakeDataStoreRepository: DataStoreRepository {
    private var location: String = "Dhaka"
    override suspend fun setLocation(city: String) {
        location = city
    }

    override fun getLocation(): Flow<String> {
        return flowOf(location)
    }
}