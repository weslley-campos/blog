package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Primitive color tokens — Layer 1
 *
 * Raw palette values organized into named ramps.
 * No semantic meaning. These are the building blocks from which
 * semantic tokens are assembled.
 *
 * Naming: {ramp}{step}  (0 = lightest, 11 = darkest)
 */

// Shadow (Purple / Indigo) — Primary brand ramp
val Shadow0 = Color(0xFFF5F3FF)
val Shadow1 = Color(0xFFEDE9FE)
val Shadow2 = Color(0xFFDDD6FE)
val Shadow3 = Color(0xFFC4B5FD)
val Shadow4 = Color(0xFFA78BFA)
val Shadow5 = Color(0xFF818CF8)
val Shadow6 = Color(0xFF6366F1)
val Shadow7 = Color(0xFF4F46E5)
val Shadow8 = Color(0xFF4338CA)
val Shadow9 = Color(0xFF3730A3)
val Shadow10 = Color(0xFF312E81)
val Shadow11 = Color(0xFF1E1B4B)

// Ocean (Cyan / Teal) — Secondary brand ramp
val Ocean0 = Color(0xFFECFEFF)
val Ocean1 = Color(0xFFCFFAFE)
val Ocean2 = Color(0xFFA5F3FC)
val Ocean3 = Color(0xFF67E8F9)
val Ocean4 = Color(0xFF22D3EE)
val Ocean5 = Color(0xFF06B6D4)
val Ocean6 = Color(0xFF0891B2)
val Ocean7 = Color(0xFF0E7490)
val Ocean8 = Color(0xFF155E75)
val Ocean9 = Color(0xFF164E63)
val Ocean10 = Color(0xFF1E3A5F)
val Ocean11 = Color(0xFF0C2340)

// Blue (Tertiary) — Supporting accent ramp
val Blue0 = Color(0xFFEFF6FF)
val Blue1 = Color(0xFFDBEAFE)
val Blue2 = Color(0xFFBFDBFE)
val Blue3 = Color(0xFF93C5FD)
val Blue4 = Color(0xFF60A5FA)
val Blue5 = Color(0xFF3B82F6)
val Blue6 = Color(0xFF2563EB)
val Blue7 = Color(0xFF1D4ED8)
val Blue8 = Color(0xFF1E40AF)
val Blue9 = Color(0xFF1E3A5F)
val Blue10 = Color(0xFF172554)

// Neutral (Grayscale) — Surfaces and text
val Neutral0 = Color(0xFFFFFFFF)
val Neutral1 = Color(0xFFF8FAFC)
val Neutral2 = Color(0xFFF1F5F9)
val Neutral3 = Color(0xFFE2E8F0)
val Neutral4 = Color(0xFFCBD5E1)
val Neutral5 = Color(0xFF94A3B8)
val Neutral6 = Color(0xFF475569)
val Neutral7 = Color(0xFF334155)
val Neutral8 = Color(0xFF1E293B)
val Neutral9 = Color(0xFF0F172A)

// Deep (Space tones) — Dark theme specific surfaces
val Deep0 = Color(0xFF1E2D50)
val Deep1 = Color(0xFF192540)
val Deep2 = Color(0xFF142040)
val Deep3 = Color(0xFF0F1930)
val Deep4 = Color(0xFF0C1730)
val Deep5 = Color(0xFF091328)
val Deep6 = Color(0xFF060E20)
val Deep7 = Color(0xFF000000)

// SlateBlue — Cool steel-blue ramp, used for text on dark surfaces
val SlateBlue3 = Color(0xFF9AADCC)
val SlateBlue5 = Color(0xFF7A8BAA)
val SlateBlue6 = Color(0xFF64748B)
val SlateBlue7 = Color(0xFF4A5A7A)

// Ice — Pale lavender tint, primary text on dark + hero gradient stop
val IceLavender = Color(0xFFDEE5FF)

// Functional — Status colors
val FunctionalGreen = Color(0xFF059669)
val FunctionalGreenBright = Color(0xFF34D399)
val FunctionalGreenLight = Color(0xFFECFDF5)
val FunctionalGreenDark = Color(0xFF064E3B)
val EmeraldDeep = Color(0xFF0A3A2A)

val FunctionalAmber = Color(0xFFD97706)
val FunctionalAmberBright = Color(0xFFFBBF24)
val FunctionalAmberLight = Color(0xFFFFFBEB)
val FunctionalAmberDeep = Color(0xFF92400E)
val FunctionalAmberDark = Color(0xFF78350F)
val RustDeep = Color(0xFF3A2510)

val FunctionalRed = Color(0xFFDC2626)
val FunctionalRedBright = Color(0xFFF87171)
val FunctionalRedLight = Color(0xFFFEF2F2)
val FunctionalRedDeep = Color(0xFF991B1B)
val FunctionalRedDark = Color(0xFF7F1D1D)

val FunctionalBlue = Color(0xFF3B82F6)
val FunctionalBlueLight = Color(0xFFEFF6FF)
val FunctionalBlueDark = Color(0xFF172554)

// Syntax highlighting — Code block specific
val SyntaxKeyword = Color(0xFFC084FC)
val SyntaxFunction = Color(0xFF4ADE80)
val SyntaxType = Color(0xFF22D3EE)
val SyntaxString = Color(0xFFFBBF24)
val SyntaxError = Color(0xFFF87171)
val SyntaxDefault = Color(0xFFE2E8F0)
val SyntaxComment = Color(0xFF64748B)

/**
 * Semantic color tokens — Layer 2
 *
 * This is the THEME INTERFACE. Every theme must provide a complete mapping.
 * A theme is just a [BlogColors] instance with different primitive assignments.
 *
 * Token naming follows purpose, not appearance:
 *   background       — NOT "dark-blue" or "white"
 *   textPrimary      — NOT "light-gray" or "near-black"
 *
 * Adding a new theme (Solarized, High Contrast, etc.) = creating a new
 * [BlogColors] instance. Zero component changes needed.
 */
@Immutable
data class BlogColors(
    // ── Surface ──────────────────────────────────────────────
    val background: Color,
    val surfaceElevated: Color,
    val surfaceSunken: Color,
    val surfaceNav: Color,
    val surfaceNavOpacity: Float,
    val surfaceBrandSubtle: List<Color>,
    // ── Text ─────────────────────────────────────────────────
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val textInverse: Color,
    val textLink: Color = textPrimary,
    // ── Brand ────────────────────────────────────────────────
    val brand: Color,
    val brandSecondary: Color,
    val gradientBrand: List<Color>,
    val gradientHero: List<Color>,
    val gradientCardThumbnail: List<Color>,
    // ── Interactive ──────────────────────────────────────────
    val interactivePrimaryFill: List<Color> = gradientBrand,
    val interactivePrimaryText: Color = textInverse,
    val interactivePrimaryHoverFill: List<Color> = gradientBrand,
    val interactiveSecondaryFill: Color,
    val interactiveSecondaryText: Color = textPrimary,
    val interactiveGhostText: Color = brand,
    val interactiveGhostHoverFill: Color,
    // ── Border & Divider ─────────────────────────────────────
    val borderActive: Color = brand,
    val dividerGradientColor: Color,
    val dividerGradientOpacity: Float,
    // ── Status ───────────────────────────────────────────────
    val statusInfoAccent: Color,
    val statusInfoBackground: Color,
    val statusInfoText: Color,
    val statusSuccessAccent: Color,
    val statusSuccessBackground: Color,
    val statusSuccessText: Color,
    val statusWarningAccent: Color,
    val statusWarningBackground: Color,
    val statusWarningText: Color,
    val statusDangerAccent: Color,
    val statusDangerBackground: Color,
    val statusDangerText: Color,
    // ── Tags ─────────────────────────────────────────────────
    val tagTypescriptBackground: Color,
    val tagTypescriptText: Color,
    val tagRustBackground: Color,
    val tagRustText: Color,
    val tagReactBackground: Color,
    val tagReactText: Color,
    val tagGoBackground: Color,
    val tagGoText: Color,
    val tagDefaultBackground: Color,
    val tagDefaultText: Color,
    // ── Code Block ───────────────────────────────────────────
    val codeBackground: Color,
    val codeHeader: Color,
    // ── Shadows ──────────────────────────────────────────────
    val shadowNavAlpha: Float,
    val shadowElevatedAlpha: Float,
    val shadowButtonColor: Color = brand,
    val shadowButtonAlpha: Float,
    val shadowAmbientEnabled: Boolean,
    // ── Nav specific ─────────────────────────────────────────
    val navSlate: Color,
    val navBlur: Float = 24f,
    val isDark: Boolean,
)

/**
 * ── Dark Theme ──────────────────────────────────────────────
 * Deep-space editorial aesthetic. "The Luminous Curator."
 */
val DarkColorPalette = BlogColors(
    // Surface
    background = Deep6,
    surfaceElevated = Deep3,
    surfaceSunken = Deep7,
    surfaceNav = Deep5,
    surfaceNavOpacity = 0.6f,
    surfaceBrandSubtle = listOf(
        Shadow5.copy(alpha = 0.08f),
        Ocean4.copy(alpha = 0.08f),
    ),
    // Text
    textPrimary = IceLavender,
    textSecondary = SlateBlue5,
    textMuted = SlateBlue7,
    textInverse = Deep6,
    textLink = Shadow5,
    // Brand
    brand = Shadow5,
    brandSecondary = Ocean4,
    gradientBrand = listOf(Shadow5, Ocean4),
    gradientHero = listOf(IceLavender, Shadow5, Ocean4),
    gradientCardThumbnail = listOf(Deep4, Deep0),
    // Interactive
    interactivePrimaryText = Deep6,
    interactiveSecondaryFill = Deep1,
    interactiveGhostHoverFill = Shadow5.copy(alpha = 0.06f),
    // Border & Divider
    dividerGradientColor = Shadow6,
    dividerGradientOpacity = 0.25f,
    // Status
    statusInfoAccent = FunctionalBlue,
    statusInfoBackground = FunctionalBlueDark,
    statusInfoText = SlateBlue5,
    statusSuccessAccent = Ocean4,
    statusSuccessBackground = Ocean9.copy(alpha = 0.3f),
    statusSuccessText = SlateBlue5,
    statusWarningAccent = FunctionalAmberBright,
    statusWarningBackground = FunctionalAmberDark.copy(alpha = 0.3f),
    statusWarningText = SlateBlue5,
    statusDangerAccent = FunctionalRedBright,
    statusDangerBackground = FunctionalRedDark.copy(alpha = 0.3f),
    statusDangerText = SlateBlue5,
    // Tags
    tagTypescriptBackground = Ocean10,
    tagTypescriptText = Ocean4,
    tagRustBackground = RustDeep,
    tagRustText = FunctionalAmberBright,
    tagReactBackground = EmeraldDeep,
    tagReactText = FunctionalGreenBright,
    tagGoBackground = EmeraldDeep,
    tagGoText = FunctionalGreenBright,
    tagDefaultBackground = Deep2,
    tagDefaultText = SlateBlue3,
    // Code (always dark — even in light theme)
    codeBackground = Deep7,
    codeHeader = Deep5,
    // Shadows
    shadowNavAlpha = 0.3f,
    shadowElevatedAlpha = 0.25f,
    shadowButtonAlpha = 0.25f,
    shadowAmbientEnabled = true,
    // Nav
    navSlate = SlateBlue6,
    isDark = true,
)

/**
 * ── Light Theme ─────────────────────────────────────────────
 * Clean editorial. Same gradient DNA, adapted for bright surfaces.
 */
val LightColorPalette = BlogColors(
    // Surface
    background = Neutral0,
    surfaceElevated = Neutral0,
    surfaceSunken = Neutral8,
    surfaceNav = Neutral0,
    surfaceNavOpacity = 0.88f,
    surfaceBrandSubtle = listOf(
        Shadow6.copy(alpha = 0.05f),
        Ocean6.copy(alpha = 0.05f),
    ),
    // Text
    textPrimary = Neutral9,
    textSecondary = Neutral6,
    textMuted = Neutral5,
    textInverse = Neutral0,
    textLink = Shadow6,
    // Brand
    brand = Shadow6,
    brandSecondary = Ocean6,
    gradientBrand = listOf(Shadow6, Ocean6),
    gradientHero = listOf(Neutral9, Shadow6, Ocean6),
    gradientCardThumbnail = listOf(Shadow0, Ocean0),
    // Interactive
    interactivePrimaryText = Neutral0,
    interactiveSecondaryFill = Neutral2,
    interactiveGhostHoverFill = Shadow0,
    // Border & Divider
    dividerGradientColor = Shadow6,
    dividerGradientOpacity = 0.15f,
    // Status
    statusInfoAccent = FunctionalBlue,
    statusInfoBackground = FunctionalBlueLight,
    statusInfoText = Blue8,
    statusSuccessAccent = Ocean6,
    statusSuccessBackground = Ocean0,
    statusSuccessText = Ocean8,
    statusWarningAccent = FunctionalAmber,
    statusWarningBackground = FunctionalAmberLight,
    statusWarningText = FunctionalAmberDeep,
    statusDangerAccent = FunctionalRed,
    statusDangerBackground = FunctionalRedLight,
    statusDangerText = FunctionalRedDeep,
    // Tags
    tagTypescriptBackground = Ocean0,
    tagTypescriptText = Ocean6,
    tagRustBackground = FunctionalAmberLight,
    tagRustText = FunctionalAmber,
    tagReactBackground = FunctionalGreenLight,
    tagReactText = FunctionalGreen,
    tagGoBackground = FunctionalGreenLight,
    tagGoText = FunctionalGreen,
    tagDefaultBackground = Neutral2,
    tagDefaultText = Neutral6,
    // Code (stays dark for contrast)
    codeBackground = Neutral8,
    codeHeader = Neutral9,
    // Shadows
    shadowNavAlpha = 0.06f,
    shadowElevatedAlpha = 0.06f,
    shadowButtonAlpha = 0.25f,
    shadowAmbientEnabled = false,
    // Nav
    navSlate = Neutral5,
    isDark = false,
)

/**
 * ── CompositionLocal ────────────────────────────────────────
 * Static because theme changes are rare (user toggles, not per-frame).
 */
val LocalBlogColors = staticCompositionLocalOf<BlogColors> {
    error("No BlogColors provided. Wrap your content in BlogTheme { ... }")
}
