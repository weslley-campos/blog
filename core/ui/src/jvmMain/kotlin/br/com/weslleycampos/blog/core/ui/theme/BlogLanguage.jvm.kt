package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale
import java.util.Locale as JvmLocale

actual object BlogLanguage {
    private val LocalBlogLanguage = staticCompositionLocalOf {
        Locale(languageTag = JvmLocale.getDefault().toLanguageTag())
    }

    actual val current: Locale
        @Composable get() = LocalBlogLanguage.current


    @Composable
    actual infix fun provides(value: Locale): ProvidedValue<*> {
        val locale = JvmLocale.forLanguageTag(value.toLanguageTag())

        JvmLocale.setDefault(locale)
        return LocalBlogLanguage.provides(value)
    }
}