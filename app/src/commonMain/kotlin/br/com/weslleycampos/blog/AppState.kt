package br.com.weslleycampos.blog

import androidx.compose.ui.text.intl.Locale
import br.com.weslleycampos.blog.core.ui.constants.defaultLanguage

data class AppState(
    val isDarkThemeEnabled: Boolean? = null,
    val blogLanguage: Locale = Locale(defaultLanguage),
)
