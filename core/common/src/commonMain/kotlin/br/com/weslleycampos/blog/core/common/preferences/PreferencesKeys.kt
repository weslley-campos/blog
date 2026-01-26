package br.com.weslleycampos.blog.core.common.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val isDarkThemeEnabled = booleanPreferencesKey("is_dark_theme_enabled")
    val language = stringPreferencesKey("language")
}
