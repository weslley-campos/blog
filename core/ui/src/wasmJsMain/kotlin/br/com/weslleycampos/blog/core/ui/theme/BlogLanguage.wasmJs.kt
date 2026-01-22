package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale

actual object BlogLanguage {
    private val LocalBlogLanguage = staticCompositionLocalOf { Locale.current }

    actual val current: Locale
        @Composable get() = LocalBlogLanguage.current

    @Composable
    actual infix fun provides(value: Locale): ProvidedValue<*> {
        window.__customLocale = value.toLanguageTag().replace('_', '-')
        return LocalBlogLanguage.provides(value)
    }
}

@Suppress("ClassNaming", "ObjectPropertyNaming")
external object window {
    var __customLocale: String?
}
