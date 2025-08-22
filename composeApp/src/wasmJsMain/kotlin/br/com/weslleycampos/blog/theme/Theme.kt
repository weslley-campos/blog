package br.com.weslleycampos.blog.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun BlogTheme(
    isDarkMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (isDarkMode) darkBlogColors else lightBlogColors
    val typography = BlogTypography
    CompositionLocalProvider(
        values = arrayOf(
            LocalBlogColors provides colors,
            LocalBlogTypography provides typography
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
