package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.inter
import br.com.weslleycampos.blog.core.ui.resources.jetbrains_mono
import br.com.weslleycampos.blog.core.ui.resources.merriweather
import br.com.weslleycampos.blog.core.ui.resources.shrikhand
import org.jetbrains.compose.resources.Font

val InterFontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(CoreUiRes.font.inter),
        Font(CoreUiRes.font.inter, FontWeight.Normal),
        Font(CoreUiRes.font.inter, FontWeight.Light),
        Font(CoreUiRes.font.inter, FontWeight.Medium),
        Font(CoreUiRes.font.inter, FontWeight.SemiBold),
        Font(CoreUiRes.font.inter, FontWeight.Bold),
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
        Font(CoreUiRes.font.jetbrains_mono, FontWeight.Light),
        Font(CoreUiRes.font.jetbrains_mono, FontWeight.Medium),
        Font(CoreUiRes.font.jetbrains_mono, FontWeight.SemiBold),
        Font(CoreUiRes.font.jetbrains_mono, FontWeight.Bold),
    )

val ShrikhandFontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(CoreUiRes.font.shrikhand),
    )

val blogTypography: Typography
    @Composable get() = Typography(
        // --- LEVEL 1: DISPLAY & HERO ---
        // Used for: Markdown H1 (# Title)
        displayLarge = TextStyle(
            fontFamily = MerriWeatherFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            lineHeight = 56.sp,
            letterSpacing = (-1.5).sp
        ),
        // --- SPECIAL: BLOCKQUOTES ---
        // Used for: Markdown Blockquotes (> Quote)
        // Context: Editorial quotes that break the reading flow.
        displayMedium = TextStyle(
            fontFamily = MerriWeatherFontFamily,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
        ),
        // --- LEVEL 2: MAJOR SECTIONS ---
        // Used for: Markdown H2 (## Section)
        headlineLarge = TextStyle(
            fontFamily = MerriWeatherFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
        ),
        // --- LEVEL 3: SUBSECTIONS ---
        // Used for: Markdown H3 (### Subsection)
        headlineSmall = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
        ),
        // --- LEVEL 4: GROUP HEADERS ---
        // Used for: Markdown H4 (#### Title)
        titleLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        ),
        // --- LEVEL 5: SMALL HEADERS ---
        // Used for: Markdown H5 (##### Title)
        titleMedium = TextStyle(
            fontFamily = MerriWeatherFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        ),
        // --- LEVEL 6: TINY HEADERS ---
        // Used for: Markdown H6 (###### Title)
        titleSmall = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
        // --- BODY TEXT (PARAGRAPHS) ---
        // Used for: Standard Markdown Paragraph (p)
        bodyLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp
        ),
        // --- CODE ---
        labelLarge = TextStyle(
            fontFamily = JetBrainsMonoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        )
    )

val LocalBlogTypography = staticCompositionLocalOf<Typography> {
    error("No BlogTypography provided")
}
