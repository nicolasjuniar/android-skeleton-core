package juniar.nicolas.androidskeletoncore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("user_prefs")

class GenericDataStoreHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }

    fun <T> load(key: Preferences.Key<T>): Flow<T?> {
        return context.dataStore.data.map { prefs ->
            prefs[key]
        }
    }

    inline fun <reified T> createKey(name: String): Preferences.Key<T> {
        return when (T::class) {
            String::class -> stringPreferencesKey(name) as Preferences.Key<T>
            Int::class -> intPreferencesKey(name) as Preferences.Key<T>
            Boolean::class -> booleanPreferencesKey(name) as Preferences.Key<T>
            Float::class -> floatPreferencesKey(name) as Preferences.Key<T>
            Long::class -> longPreferencesKey(name) as Preferences.Key<T>
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }
}