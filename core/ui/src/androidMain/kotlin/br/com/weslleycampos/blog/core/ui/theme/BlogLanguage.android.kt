package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import java.util.Locale as AndroidLocale
import androidx.compose.ui.platform.LocalLocale

actual object BlogLanguage {
    actual val current: Locale
        @Composable get() = Locale(LocalLocale.current.platformLocale)

    @Composable
    actual infix fun provides(value: Locale): ProvidedValue<*> {
        val configuration = LocalConfiguration.current

        val locale = AndroidLocale.forLanguageTag(value.toLanguageTag())
        AndroidLocale.setDefault(locale)
        configuration.setLocale(locale)

        LocalContext.current.createConfigurationContext(configuration)
        return LocalConfiguration.provides(configuration)
    }
}
