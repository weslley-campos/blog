package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Light theme colors
// Background Colors
val white = Color(0xFFFFFFFF)
val whisper = Color(0xFFF3F3F5)

// Text Colors
val charcoal = Color(0xFF252525)
val slate = Color(0xFF717182)

// Primary Colors
val midnight = Color(0xFF030213)

// Secondary & Accent Colors
val cloud = Color(0xFFF1F2F7)
val silver = Color(0xFFE9EBEF)

// Muted Colors
val mist = Color(0xFFECECF0)

// Error Colors
val cherry = Color(0xFFD4183D)

// Border & Outline
val outline = Color(0xFFCBCED4)
val outlineLight = Color(0x1A000000)

// Sidebar Colors
val pearl = Color(0xFFFBFBFB)
val ivory = Color(0xFFF7F7F7)
val ash = Color(0xFFEBEBEB)

// Dark theme colors
// Background Colors
val darkCharcoal = Color(0xFF252525)
val graphiteGray = Color(0xFF444444)

// Text Colors
val almostWhite = Color(0xFFFBFBFB)
val silverMist = Color(0xFFB5B5B5)

// Primary Colors
val smokeGray = Color(0xFF343434)

// Error Colors
val coralRed = Color(0xFFE74C3C)
val roseBlush = Color(0xFFF8D7DA)

// Border & Outline
val steelGray = Color(0xFF707070)

val emeraldGreen = Color(0xFF219653)
val fireOpal = Color(0xFFFEB5757)
val skyBlue = Color(0xFF2D9CDB)
val warmOrange = Color(0xFFF2994A)

open class ExtendedColors {
    val primary = midnight
    val success = emeraldGreen
    val warning = warmOrange
    val info = skyBlue
    val error = fireOpal
}

data class LayoutColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
) : ExtendedColors()

data class TextColors(
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
) : ExtendedColors()

data class BorderColors(
    val outline: Color,
    val outlineVariant: Color,
) : ExtendedColors()

data class IconColors(
    val neutral: Color,
) : ExtendedColors()

data class BlogColors(
    val layout: LayoutColors,
    val text: TextColors,
    val border: BorderColors,
    val icon: IconColors,
)

val lightBlogColors = BlogColors(
    layout = LayoutColors(
        background = white,
        surface = whisper,
        surfaceVariant = cloud,
    ),
    text = TextColors(
        onPrimary = charcoal,
        onBackground = charcoal,
        onSurface = slate,
        onSurfaceVariant = charcoal,
    ),
    border = BorderColors(
        outline = outline,
        outlineVariant = outlineLight,
    ),
    icon = IconColors(
        neutral = charcoal,
    )
)

val darkBlogColors = BlogColors(
    layout = LayoutColors(
        background = darkCharcoal,
        surface = graphiteGray,
        surfaceVariant = smokeGray,
    ),
    text = TextColors(
        onPrimary = almostWhite,
        onBackground = almostWhite,
        onSurface = silverMist,
        onSurfaceVariant = almostWhite,
    ),
    border = BorderColors(
        outline = steelGray,
        outlineVariant = steelGray,
    ),
    icon = IconColors(
        neutral = almostWhite,
    )
)

val LocalBlogColors = staticCompositionLocalOf<BlogColors> {
    error("No BlogColors provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colorScheme] in preference to [BlogTheme.colors].
 */
fun debugColors(debugColor: Color = Color.Magenta) = ColorScheme(
    primary = debugColor,
    onPrimary = debugColor,
    primaryContainer = debugColor,
    onPrimaryContainer = debugColor,
    inversePrimary = debugColor,
    secondary = debugColor,
    onSecondary = debugColor,
    secondaryContainer = debugColor,
    onSecondaryContainer = debugColor,
    tertiary = debugColor,
    onTertiary = debugColor,
    tertiaryContainer = debugColor,
    onTertiaryContainer = debugColor,
    background = debugColor,
    onBackground = debugColor,
    surface = debugColor,
    onSurface = debugColor,
    surfaceVariant = debugColor,
    onSurfaceVariant = debugColor,
    surfaceTint = debugColor,
    inverseSurface = debugColor,
    inverseOnSurface = debugColor,
    error = debugColor,
    onError = debugColor,
    errorContainer = debugColor,
    onErrorContainer = debugColor,
    outline = debugColor,
    outlineVariant = debugColor,
    scrim = debugColor,
    surfaceBright = debugColor,
    surfaceDim = debugColor,
    surfaceContainer = debugColor,
    surfaceContainerHigh = debugColor,
    surfaceContainerHighest = debugColor,
    surfaceContainerLow = debugColor,
    surfaceContainerLowest = debugColor,
)
