package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// =============================================================================
// Base Color Palette - Light Theme
// =============================================================================

// Background Colors
val white = Color(0xFFFFFFFF)
val whisper = Color(0xFFF3F3F5)
val snow = Color(0xFFF9FAFB)

// Text Colors
val charcoal = Color(0xFF252525)
val slate = Color(0xFF717182)
val stoneGray = Color(0xFF6B7280)

// Primary Colors
val midnight = Color(0xFF030213)
val gunmetal = Color(0xFF1F2937)

// Secondary & Accent Colors
val cloud = Color(0xFFF1F2F7)
val silver = Color(0xFFE9EBEF)
val platinum = Color(0xFFF3F4F6)

// Muted Colors
val mist = Color(0xFFECECF0)

// Error Colors
val cherry = Color(0xFFD4183D)

// Border & Outline
val outline = Color(0xFFCBCED4)
val outlineLight = Color(0x1A000000)
val fog = Color(0xFFE5E7EB)

// Sidebar Colors
val pearl = Color(0xFFFBFBFB)
val ivory = Color(0xFFF7F7F7)
val ash = Color(0xFFEBEBEB)

// =============================================================================
// Base Color Palette - Dark Theme
// =============================================================================

// Background Colors
val darkCharcoal = Color(0xFF252525)
val graphiteGray = Color(0xFF444444)
val onyx = Color(0xFF343434)

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
val charcoalBorder = Color(0xFF555555)

// =============================================================================
// Semantic Colors (shared between themes)
// =============================================================================

val emeraldGreen = Color(0xFF219653)
val fireOpal = Color(0xFFEB5757)
val skyBlue = Color(0xFF2D9CDB)
val warmOrange = Color(0xFFF2994A)

// =============================================================================
// Extended Colors Base Class
// =============================================================================

open class ExtendedColors {
    open val primary = midnight
    open val success = emeraldGreen
    open val warning = warmOrange
    open val info = skyBlue
    open val error = fireOpal
}

// =============================================================================
// Color Data Classes
// =============================================================================

data class LayoutColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
) : ExtendedColors()

data class TextColors(
    override val primary: Color,
    val secondary: Color,
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
) : ExtendedColors()

data class BorderColors(
    val gray: Color,
    val outline: Color,
    val outlineVariant: Color,
) : ExtendedColors()

data class IconColors(val neutral: Color) : ExtendedColors()

data class ButtonColors(
    override val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val ghost: Color,
    val onGhost: Color,
    val outline: Color,
    val onOutline: Color,
) : ExtendedColors()

data class CardColors(
    val background: Color,
    val onBackground: Color,
    val border: Color,
) : ExtendedColors()

data class InputColors(
    val background: Color,
    val border: Color,
    val focusBorder: Color,
    val text: Color,
    val placeholder: Color,
) : ExtendedColors()

data class ChipColors(
    val background: Color,
    val onBackground: Color,
    val border: Color,
    val selectedBackground: Color,
    val onSelectedBackground: Color,
) : ExtendedColors()

data class BadgeColors(
    val successBackground: Color,
    val successText: Color,
    val warningBackground: Color,
    val warningText: Color,
    val errorBackground: Color,
    val errorText: Color,
    val infoBackground: Color,
    val infoText: Color,
) : ExtendedColors()

data class BlogColors(
    val layout: LayoutColors,
    val text: TextColors,
    val border: BorderColors,
    val icon: IconColors,
    val button: ButtonColors,
    val card: CardColors,
    val input: InputColors,
    val chip: ChipColors,
    val badge: BadgeColors,
)

// =============================================================================
// Light Theme Colors
// =============================================================================

val lightBlogColors = BlogColors(
    layout = LayoutColors(
        background = white,
        surface = whisper,
        surfaceVariant = cloud,
    ),
    text = TextColors(
        primary = charcoal,
        secondary = slate,
        onPrimary = white,
        onBackground = charcoal,
        onSurface = slate,
        onSurfaceVariant = charcoal,
    ),
    border = BorderColors(
        gray = fog,
        outline = outline,
        outlineVariant = outlineLight,
    ),
    icon = IconColors(neutral = charcoal),
    button = ButtonColors(
        primary = gunmetal,
        onPrimary = white,
        secondary = platinum,
        onSecondary = gunmetal,
        ghost = Color.Transparent,
        onGhost = charcoal,
        outline = Color.Transparent,
        onOutline = charcoal,
    ),
    card = CardColors(
        background = white,
        onBackground = charcoal,
        border = fog,
    ),
    input = InputColors(
        background = white,
        border = fog,
        focusBorder = gunmetal,
        text = charcoal,
        placeholder = stoneGray,
    ),
    chip = ChipColors(
        background = platinum,
        onBackground = charcoal,
        border = fog,
        selectedBackground = gunmetal,
        onSelectedBackground = white,
    ),
    badge = BadgeColors(
        successBackground = emeraldGreen.copy(alpha = 0.15f),
        successText = emeraldGreen,
        warningBackground = warmOrange.copy(alpha = 0.15f),
        warningText = warmOrange,
        errorBackground = fireOpal.copy(alpha = 0.15f),
        errorText = fireOpal,
        infoBackground = skyBlue.copy(alpha = 0.15f),
        infoText = skyBlue,
    ),
)

// =============================================================================
// Dark Theme Colors
// =============================================================================

val darkBlogColors = BlogColors(
    layout = LayoutColors(
        background = darkCharcoal,
        surface = graphiteGray,
        surfaceVariant = smokeGray,
    ),
    text = TextColors(
        primary = almostWhite,
        secondary = silverMist,
        onPrimary = darkCharcoal,
        onBackground = almostWhite,
        onSurface = silverMist,
        onSurfaceVariant = almostWhite,
    ),
    border = BorderColors(
        gray = charcoalBorder,
        outline = steelGray,
        outlineVariant = steelGray,
    ),
    icon = IconColors(neutral = almostWhite),
    button = ButtonColors(
        primary = almostWhite,
        onPrimary = darkCharcoal,
        secondary = smokeGray,
        onSecondary = almostWhite,
        ghost = Color.Transparent,
        onGhost = almostWhite,
        outline = Color.Transparent,
        onOutline = almostWhite,
    ),
    card = CardColors(
        background = graphiteGray,
        onBackground = almostWhite,
        border = charcoalBorder,
    ),
    input = InputColors(
        background = smokeGray,
        border = charcoalBorder,
        focusBorder = almostWhite,
        text = almostWhite,
        placeholder = silverMist,
    ),
    chip = ChipColors(
        background = smokeGray,
        onBackground = almostWhite,
        border = charcoalBorder,
        selectedBackground = almostWhite,
        onSelectedBackground = darkCharcoal,
    ),
    badge = BadgeColors(
        successBackground = emeraldGreen.copy(alpha = 0.2f),
        successText = emeraldGreen,
        warningBackground = warmOrange.copy(alpha = 0.2f),
        warningText = warmOrange,
        errorBackground = coralRed.copy(alpha = 0.2f),
        errorText = coralRed,
        infoBackground = skyBlue.copy(alpha = 0.2f),
        infoText = skyBlue,
    ),
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
