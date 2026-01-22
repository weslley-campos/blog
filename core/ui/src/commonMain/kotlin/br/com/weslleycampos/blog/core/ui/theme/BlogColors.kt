package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/** Color Transparency
 100% — FF
 95% — F2
 90% — E6
 85% — D9
 80% — CC
 75% — BF
 70% — B3
 65% — A6
 60% — 99
 55% — 8C
 50% — 80
 45% — 73
 40% — 66
 35% — 59
 30% — 4D
 25% — 40
 20% — 33
 15% — 26
 10% — 1A
 5% — 0D
 0% — 00
*/

// Primary Color
private val Green = Color(0xFF008000)

// App Colors LM
private val Wash = Color(0xFFF4F4F4)
private val Mist = Color(0xFFE4E4E7)

// App Colors DM
private val Obsidian = Color(0xFF191919)
private val Black = Color(0xFF18181B)

// Text Colors
private val White = Color(0xFFFAFAFA)

// Gray Colors
private val Grey = Color(0xFF71717A)
private val TenderBlack = Color(0x333F3F46)

// Semantic Colors
val Eucalyptus = Color(0xFF219653)
val RedAura = Color(0xFFEB5757)
val Turquoise = Color(0xFF2D9CDB)
val RoyalPeach = Color(0xFFF2994A)

@Immutable
data class AppColors(
    val primary: Color = Green,
    val background: Color,
    val surface: Color,
)

@Immutable
data class TextColors(
    val primary: Color,
    val onSurface: Color,
    val onPrimary: Color,
)

@Immutable
data class BorderColors(
    val primary: Color = Green,
    val default: Color,
    val active: Color,
    val divider: Color
)

@Immutable
data class IconColors(
    val primary: Color,
    val onSurface: Color,
)

@Immutable
data class ChipColors(
    val container: Color,
    val label: Color
)

@Immutable
data class BadgeColors(
    val info: Color = Turquoise,
    val success: Color = Eucalyptus,
    val warning: Color = RoyalPeach,
    val error: Color = RedAura
)

@Immutable
data class BlogColors(
    val app: AppColors,
    val text: TextColors,
    val border: BorderColors,
    val icon: IconColors,
    val chip: ChipColors,
    val badge: BadgeColors
)

private val appColors = AppColors(
    background = Mist,
    surface = Wash
)

val LightColors = BlogColors(
    app = appColors,
    text = TextColors(
        primary = appColors.primary,
        onSurface = Black,
        onPrimary = White
    ),
    border = BorderColors(
        default = Mist,
        active = Grey,
        divider = TenderBlack
    ),
    icon = IconColors(
        primary = appColors.primary,
        onSurface = Black,
    ),
    chip = ChipColors(
        container = Mist,
        label = Black
    ),
    badge = BadgeColors()
)

val DarkColors = BlogColors(
    app = AppColors(
        background = Black,
        surface = Obsidian
    ),
    text = TextColors(
        primary = appColors.primary,
        onSurface = Mist,
        onPrimary = White
    ),
    border = BorderColors(
        default = Grey,
        active = Mist,
        divider = Grey.copy(alpha = 0.3f)
    ),
    icon = IconColors(
        primary = appColors.primary,
        onSurface = Mist,
    ),
    chip = ChipColors(
        container = Grey.copy(alpha = 0.2f),
        label = Wash
    ),
    badge = BadgeColors()
)

val LocalBlogColors = staticCompositionLocalOf<BlogColors> {
    error("No BlogColors provided")
}
