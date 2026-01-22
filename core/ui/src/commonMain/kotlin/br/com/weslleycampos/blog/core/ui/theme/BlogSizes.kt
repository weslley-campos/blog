package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class BlogIconSize(
    val xSmall: Dp = 16.dp,
    val small: Dp = 20.dp,
    val medium: Dp = 24.dp,
    val large: Dp = 32.dp,
    val xLarge: Dp = 48.dp,
)

@Immutable
data class BlogSizes(
    val icon: BlogIconSize = BlogIconSize(),
)

val LocalBlogSizes = staticCompositionLocalOf<BlogSizes> {
    error("No BlogSizes provided")
}
