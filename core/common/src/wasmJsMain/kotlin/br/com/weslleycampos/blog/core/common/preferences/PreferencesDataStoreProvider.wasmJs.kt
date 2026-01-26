package br.com.weslleycampos.blog.core.common.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.core.okio.WebStorage
import androidx.datastore.core.okio.WebStorageType
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Single
actual fun providePreferencesDataStore(scope: Scope): DataStore<Preferences> {
    val sessionWebStorage = WebStorage(
        name = preferencesDataStoreFileName,
        serializer = PreferencesSerializer,
        storageType = WebStorageType.SESSION,
    )
    return PreferenceDataStoreFactory.create(sessionWebStorage)
}
