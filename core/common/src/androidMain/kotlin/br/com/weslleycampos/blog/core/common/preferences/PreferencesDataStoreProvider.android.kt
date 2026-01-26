package br.com.weslleycampos.blog.core.common.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Single
actual fun providePreferencesDataStore(scope: Scope): DataStore<Preferences> {
    val context: Context = scope.get()

    return createPreferencesDataStore {
        context.filesDir.resolve(preferencesDataStoreFileName).absolutePath
    }
}