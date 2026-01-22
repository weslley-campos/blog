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
 * - Extra Small (xs): 4dp
 * - Small (sm): 8dp
 * - Medium (md): 12dp
 * - Large (lg): 16dp
 * - Extra Large (xl): 24dp
 * - Full: 50% (pill shape)
 */
@Immutable
data class BlogShapes(
    val none: Shape = RoundedCornerShape(0.dp),
    val extraSmall: Shape = RoundedCornerShape(4.dp),
    val small: Shape = RoundedCornerShape(8.dp),
    val medium: Shape = RoundedCornerShape(12.dp),
    val large: Shape = RoundedCornerShape(16.dp),
    val extraLarge: Shape = RoundedCornerShape(24.dp),
    val full: Shape = RoundedCornerShape(percent = 50),
)

val LocalBlogShapes = staticCompositionLocalOf<BlogShapes> {
    error("No BlogShapes provided")
}
