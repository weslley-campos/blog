package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush

/**
 * Gradient tokens — Layer 3 (Component tokens)
 *
 * Pre-built gradient brushes that reference semantic color tokens.
 * Components use these instead of building gradients from raw colors.
 *
 * Access via: BlogTheme.gradients.brand, BlogTheme.gradients.hero, etc.
 */
@Immutable
data class BlogGradients(
    /** Primary brand gradient — CTA buttons, hero accents. */
    val brand: Brush,
    /** Hero headline text gradient — 3-stop display text. */
    val hero: Brush,
    /** Subtle brand tint — CTA section backgrounds, newsletter cards. */
    val brandSubtle: Brush,
    /** Card thumbnail placeholder gradient. */
    val cardThumbnail: Brush,
    /** Nav bottom divider — fades transparent → accent → transparent. */
    val navDivider: Brush,
)

@Composable
fun blogGradients(colors: BlogColors = BlogTheme.colors): BlogGradients = BlogGradients(
    brand = Brush.horizontalGradient(colors.gradientBrand),
    hero = Brush.horizontalGradient(colors.gradientHero),
    brandSubtle = Brush.horizontalGradient(colors.surfaceBrandSubtle),
    cardThumbnail = Brush.linearGradient(colors.gradientCardThumbnail),
    navDivider = Brush.horizontalGradient(
        colors = listOf(
            colors.dividerGradientColor.copy(alpha = 0f),
            colors.dividerGradientColor.copy(alpha = colors.dividerGradientOpacity),
            colors.dividerGradientColor.copy(alpha = 0f),
        )
    ),
)

val LocalBlogGradients = staticCompositionLocalOf<BlogGradients> {
    error("No BlogGradients provided. Wrap your content in BlogTheme { ... }")
}
