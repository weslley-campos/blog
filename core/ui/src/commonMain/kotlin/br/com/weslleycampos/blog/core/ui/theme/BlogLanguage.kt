package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.text.intl.Locale

expect object BlogLanguage {
    val current: Locale @Composable get

    @Composable infix fun provides(value: Locale): ProvidedValue<*>
}
