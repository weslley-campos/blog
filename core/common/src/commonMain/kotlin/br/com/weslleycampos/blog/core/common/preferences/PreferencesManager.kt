package br.com.weslleycampos.blog.core.common.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

interface PreferencesManager {
    fun <T> get(key: Preferences.Key<T>): Flow<T?>
    fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> set(key: Preferences.Key<T>, value: T)
    suspend fun <T> remove(key: Preferences.Key<T>)
    suspend fun clearAll()
}

@Single(binds = [PreferencesManager::class])
class PreferencesManagerImpl(
    @Provided private val dataStore: DataStore<Preferences>
) : PreferencesManager {
    override fun <T> get(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    override fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    override suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> remove(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
