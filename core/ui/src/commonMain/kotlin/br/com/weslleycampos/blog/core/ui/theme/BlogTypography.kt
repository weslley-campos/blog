package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.inter
import br.com.weslleycampos.blog.core.ui.resources.jetbrains_mono
import br.com.weslleycampos.blog.core.ui.resources.merriweather
import org.jetbrains.compose.resources.Font

/**
 * Typography tokens — Semantic type scale
 *
 * Font pairing:
 *   Display/Headline: Inter (Bold/SemiBold) — geometric, authoritative
 *   Body/Label:       Inter — hyper-readable, neutral
 *   Code:             JetBrains Mono — high-contrast IDE feel
 *
 * Merriweather is available for editorial accents (blockquotes).
 */

val InterFontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(CoreUiRes.font.inter),
        Font(CoreUiRes.font.inter, FontWeight.Normal),
        Font(CoreUiRes.font.inter, FontWeight.Light),
        Font(CoreUiRes.font.inter, FontWeight.Medium),
        Font(CoreUiRes.font.inter, FontWeight.SemiBold),
        Font(CoreUiRes.font.inter, FontWeight.Bold),
        Font(CoreUiRes.font.inter, FontWeight.ExtraBold),
    )

val MerriWeatherFontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(CoreUiRes.font.merriweather),
        Font(CoreUiRes.font.merriweather, FontWeight.Normal),
        Font(CoreUiRes.font.merriweather, FontWeight.Bold),
    )

val JetBrainsMonoFontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(CoreUiRes.font.jetbrains_mono),
        Font(CoreUiRes.font.jetbrains_mono, FontWeight.Normal),
        Font(CoreUiRes.font.jetbrains_mono, FontWeight.Medium),
    )

/**
 * Semantic type scale mapping:
 *
 * displayLarge  → Hero headlines (48sp, Bold, tight tracking)
 * displayMedium → Page titles (40sp, Bold)
 * displaySmall  → Section headings (32sp, Bold)
 *
 * headlineLarge → Article H2 (28sp, Bold)
 * headlineMedium → Card titles (20sp, Bold)
 * headlineSmall → Subsection (18sp, Semi Bold)
 *
 * titleLarge    → Nav logo (22sp, Bold)
 * titleMedium   → Callout titles (16sp, Semi Bold)
 * titleSmall    → Tag labels (14sp, Semi Bold)
 *
 * bodyLarge     → Article body (17sp, Regular, relaxed line height)
 * bodyMedium    → Descriptions (15sp, Regular)
 * bodySmall     → Captions (13sp, Regular)
 *
 * labelLarge    → Nav links (15sp, Medium)
 * labelMedium   → Badges/tags (12sp, Medium, wide tracking)
 * labelSmall    → Meta info (11sp, Medium, wider tracking)
 */
val blogTypography: Typography
    @Composable get() = Typography(
        // ── Display ─────────────────────────────────────────
        displayLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            lineHeight = 56.sp,
            letterSpacing = (-0.02).sp,
        ),
        displayMedium = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            lineHeight = 48.sp,
            letterSpacing = (-0.02).sp,
        ),
        displaySmall = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.01).sp,
        ),
        // ── Headline ────────────────────────────────────────
        headlineLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 26.sp,
        ),
        // ── Title ───────────────────────────────────────────
        titleLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.02).sp,
        ),
        titleMedium = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        // ── Body ────────────────────────────────────────────
        bodyLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 30.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 24.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 20.sp,
        ),
        // ── Label ───────────────────────────────────────────
        labelLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = JetBrainsMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.5.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = JetBrainsMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 2.sp,
        ),
    )

/**
 * Code-specific typography — not part of Material's scale,
 * consumed directly by code block components.
 */
val CodeTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontFamily = JetBrainsMonoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp,
    )

/**
 * Blockquote-specific typography — editorial italic accent.
 */
val BlockquoteTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontFamily = MerriWeatherFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 32.sp,
    )

val LocalBlogTypography = staticCompositionLocalOf<Typography> {
    error("No BlogTypography provided")
}
