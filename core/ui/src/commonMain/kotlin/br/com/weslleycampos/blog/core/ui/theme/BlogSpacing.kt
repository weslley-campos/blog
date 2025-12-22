package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Blog Design System Spacing
 * Based on "Profissional" theme specification
 *
 * Uses 8pt Grid System:
 * - All spacing should be multiples of 8px (0.5rem)
 * - Minimum spacing: 4px (0.25rem)
 * - Standard spacing: 8px, 16px, 24px, 32px, 40px, 48px
 *
 * Tailwind-inspired scale for familiarity
 */
@Immutable
data class BlogSpacing(
    /** 0dp - No spacing */
    val none: Dp = 0.dp,
    /** 4dp - Extra small spacing (0.25rem) */
    val xs: Dp = 4.dp,
    /** 8dp - Small spacing (0.5rem) */
    val sm: Dp = 8.dp,
    /** 12dp - Medium-small spacing (0.75rem) */
    val md: Dp = 12.dp,
    /** 16dp - Medium spacing (1rem) - Base unit */
    val lg: Dp = 16.dp,
    /** 20dp - Medium-large spacing (1.25rem) */
    val xl: Dp = 20.dp,
    /** 24dp - Large spacing (1.5rem) */
    val xxl: Dp = 24.dp,
    /** 32dp - Extra large spacing (2rem) */
    val xxxl: Dp = 32.dp,
    /** 40dp - 2x Extra large spacing (2.5rem) */
    val huge: Dp = 40.dp,
    /** 48dp - 3x Extra large spacing (3rem) */
    val massive: Dp = 48.dp,
    /** 64dp - Section spacing (4rem) */
    val section: Dp = 64.dp,
    /** 80dp - Large section spacing (5rem) */
    val sectionLarge: Dp = 80.dp,
    /** 96dp - Extra large section spacing (6rem) */
    val sectionXLarge: Dp = 96.dp,
)

val LocalBlogSpacing = staticCompositionLocalOf<BlogSpacing> {
    error("No BlogSpacing provided")
}
