package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import br.com.weslleycampos.blog.core.ui.utils.LocalWindowType
import br.com.weslleycampos.blog.core.ui.utils.calculateWindowType

@Composable
fun BlogTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkMode) darkBlogColors else lightBlogColors
    val typography = BlogTypography
    val shapes = BlogShapes()
    val spacing = BlogSpacing()
    val windowType = currentWindowAdaptiveInfo().calculateWindowType()

    CompositionLocalProvider(
        LocalBlogColors provides colors,
        LocalBlogTypography provides typography,
        LocalBlogShapes provides shapes,
        LocalBlogSpacing provides spacing,
        LocalWindowType provides windowType,
    ) {
        MaterialTheme(
            colorScheme = debugColors(),
            content = content
        )
    }
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

    val shapes: BlogShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogShapes.current

    val spacing: BlogSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBlogSpacing.current
}
