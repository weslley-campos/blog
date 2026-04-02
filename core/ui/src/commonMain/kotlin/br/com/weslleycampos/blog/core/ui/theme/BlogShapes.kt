package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Shape tokens
 *
 * The blog's aesthetic favors generous rounding (editorial softness)
 * with sharp corners reserved for code blocks and accents.
 */
@Immutable
data class BlogShapes(
    val none: Shape = RoundedCornerShape(0.dp),
    /** Tags, badges, small chips */
    val extraSmall: Shape = RoundedCornerShape(6.dp),
    /** Buttons, inputs, callouts */
    val small: Shape = RoundedCornerShape(10.dp),
    /** Cards, code blocks */
    val medium: Shape = RoundedCornerShape(14.dp),
    /** CTA sections, large cards */
    val large: Shape = RoundedCornerShape(20.dp),
    /** Modal sheets, nav drawer */
    val extraLarge: Shape = RoundedCornerShape(24.dp),
    /** Pill shape */
    val full: Shape = RoundedCornerShape(percent = 50),
)

val LocalBlogShapes = staticCompositionLocalOf<BlogShapes> {
    error("No BlogShapes provided")
}
