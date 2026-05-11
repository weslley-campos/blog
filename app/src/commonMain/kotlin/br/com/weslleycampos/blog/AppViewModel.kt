package br.com.weslleycampos.blog

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.weslleycampos.blog.core.common.extensions.stateInWhileSubscribed
import br.com.weslleycampos.blog.core.common.preferences.PreferencesKeys
import br.com.weslleycampos.blog.core.common.preferences.PreferencesManager
import br.com.weslleycampos.blog.core.ui.constants.defaultLanguage
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AppViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    val appState = combine(
        flow = preferencesManager.get(PreferencesKeys.isDarkThemeEnabled),
        flow2 = preferencesManager.get(PreferencesKeys.language, defaultLanguage)
    ) { isDarkThemeEnabled, language ->
        AppState(
            isDarkThemeEnabled = isDarkThemeEnabled,
            blogLanguage = Locale(language)
        )
    }.stateInWhileSubscribed(
        scope = viewModelScope,
        initialValue = AppState()
    )

    fun setDarkThemeEnabled(isDarkThemeEnabled: Boolean) = viewModelScope.launch {
        preferencesManager.set(PreferencesKeys.isDarkThemeEnabled, isDarkThemeEnabled)
    }

    fun setLanguage(language: Locale) = viewModelScope.launch {
        preferencesManager.set(PreferencesKeys.language, language.toLanguageTag())
    }
}
