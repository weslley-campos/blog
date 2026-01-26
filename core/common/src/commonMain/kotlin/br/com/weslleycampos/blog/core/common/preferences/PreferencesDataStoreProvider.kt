@file:Suppress("TopLevelPropertyNaming")

package br.com.weslleycampos.blog.core.common.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

const val preferencesDataStoreFileName = "blog.preferences_pb"

fun createPreferencesDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )

@Single
expect fun providePreferencesDataStore(scope: Scope): DataStore<Preferences>
