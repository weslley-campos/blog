package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale
import br.com.weslleycampos.blog.core.ui.constants.defaultLanguage
import br.com.weslleycampos.blog.core.ui.utils.LocalScreenSize
import br.com.weslleycampos.blog.core.ui.utils.calculateScreenSize
import br.com.weslleycampos.blog.core.ui.utils.debugColorScheme

@Composable
fun BlogTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    language: Locale = Locale(defaultLanguage),
    content: @Composable () -> Unit
) {
    val screenSize = currentWindowAdaptiveInfo().calculateScreenSize()
    val colors = if (isDarkMode) DarkColors else LightColors

    val rippleConfiguration = RippleConfiguration(
        rippleAlpha = RippleAlpha(
            pressedAlpha = 0.2f,
            focusedAlpha = 0.2f,
            draggedAlpha = 0.2f,
            hoveredAlpha = 0.2f
        ),
        color = colors.app.primary
    )

    CompositionLocalProvider(
        LocalBlogColors provides colors,
        LocalBlogTypography provides blogTypography,
        LocalBlogShapes provides BlogShapes(),
        LocalBlogSpacing provides BlogSpacing(),
        LocalBlogIcons provides BlogIcons,
        LocalBlogSizes provides BlogSizes(),
        LocalDarkTheme provides isDarkMode,
        BlogLanguage provides language,
        LocalScreenSize provides screenSize,
        LocalRippleConfiguration provides rippleConfiguration,
    ) {
        MaterialTheme(
            colorScheme = debugColorScheme,
            content = content
        )
    }
}

val LocalDarkTheme = staticCompositionLocalOf<Boolean> {
    error("No DarkTheme provided")
}

object BlogTheme {
    val colors: BlogColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogColors.current

    val typography
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogTypography.current

    val icons: BlogIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogIcons.current

    val sizes: BlogSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogSizes.current

    val shapes: BlogShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogShapes.current

    val spacing: BlogSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogSpacing.current

    val language: Locale
        @Composable
        get() = BlogLanguage.current
}
