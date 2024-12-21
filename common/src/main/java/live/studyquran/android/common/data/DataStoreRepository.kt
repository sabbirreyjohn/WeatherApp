package live.studyquran.android.common.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "location")

class DataStoreRepository @Inject constructor(private val context: Context) {

    private val keyLocation = stringPreferencesKey("location")

    suspend fun setLocation(location: String) {
        context.dataStore.edit {
            it[keyLocation] = location
        }

    }

    fun getLocation() = context.dataStore.data.map { pref ->
        pref[keyLocation] ?: ""
    }
}