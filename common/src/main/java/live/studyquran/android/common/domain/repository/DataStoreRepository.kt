package live.studyquran.android.common.domain.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreRepository {
    suspend fun setLocation(location: String)
    fun getLocation(): Flow<String>
}