package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Blog Design System Shapes
 * Based on "Profissional" theme specification
 *
 * Border Radius Scale:
 * - None: 0dp
 * - Small (sm): 6dp
 * - Medium (md): 8dp
 * - Large (lg): 10dp (base radius)
 * - Extra Large (xl): 14dp
 * - Full: 9999dp (pill shape)
 */
@Immutable
data class BlogShapes(
    val none: Shape = RoundedCornerShape(0.dp),
    val small: Shape = RoundedCornerShape(6.dp),
    val medium: Shape = RoundedCornerShape(8.dp),
    val large: Shape = RoundedCornerShape(10.dp),
    val extraLarge: Shape = RoundedCornerShape(14.dp),
    val full: Shape = RoundedCornerShape(9999.dp),
)

val LocalBlogShapes = staticCompositionLocalOf<BlogShapes> {
    error("No BlogShapes provided")
}
