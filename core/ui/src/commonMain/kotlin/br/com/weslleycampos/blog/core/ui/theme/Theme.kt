package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import br.com.weslleycampos.blog.core.ui.utils.LocalWindowType
import br.com.weslleycampos.blog.core.ui.utils.calculateWindowType

@Composable
fun BlogTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkMode) darkBlogColors else lightBlogColors
    val typography = BlogTypography
    val windowType = currentWindowAdaptiveInfo().calculateWindowType()

    CompositionLocalProvider(
        values = arrayOf(
            LocalBlogColors provides colors,
            LocalBlogTypography provides typography,
            LocalWindowType provides windowType
        )
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
        get() = LocalBlogColors.current
    val typography
        @Composable
        get() = LocalBlogTypography.current
    val shapes
        @Composable
        get() = MaterialTheme.shapes
}
