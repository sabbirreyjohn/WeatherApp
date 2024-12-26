package live.studyquran.android.common.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import live.studyquran.android.common.domain.repository.DataStoreRepository
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "location")

class DataStoreRepositoryImpl @Inject constructor(private val context: Context):
    DataStoreRepository {

    private val keyLocation = stringPreferencesKey("location")

    override suspend fun setLocation(location: String) {
        context.dataStore.edit {
            it[keyLocation] = location
        }

    }

    override fun getLocation() = context.dataStore.data.map { pref ->
        pref[keyLocation] ?: ""
    }
}