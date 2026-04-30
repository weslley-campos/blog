@file:Suppress("TooManyFunctions", "MagicNumber")

package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ─────────────────────────────────────────────────────────────
// Design System Preview — "Figma Page" style
// ─────────────────────────────────────────────────────────────

@Preview(widthDp = 1400, heightDp = 5200, showBackground = true)
@Composable
fun DesignSystemPreviewLight() {
    BlogTheme(isDarkMode = false) {
        DesignSystemPage()
    }
}

@Preview(widthDp = 1400, heightDp = 5200, showBackground = true)
@Composable
fun DesignSystemPreviewDark() {
    BlogTheme(isDarkMode = true) {
        DesignSystemPage()
    }
}

@Composable
private fun DesignSystemPage() {
    val colors = BlogTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(40.dp)
    ) {
        // ── Header ──────────────────────────────────────────
        PageHeader()
        SectionDivider()

        // ── 1. Color Primitives ─────────────────────────────
        SectionTitle("1. Color Primitives")
        SectionSubtitle("Shadow (Primary Brand Ramp)")
        ColorRampRow(
            listOf(
                "0" to Shadow0, "1" to Shadow1, "2" to Shadow2, "3" to Shadow3,
                "4" to Shadow4, "5" to Shadow5, "6" to Shadow6, "7" to Shadow7,
                "8" to Shadow8, "9" to Shadow9, "10" to Shadow10, "11" to Shadow11,
            )
        )
        Spacer(Modifier.height(24.dp))

        SectionSubtitle("Ocean (Secondary Brand Ramp)")
        ColorRampRow(
            listOf(
                "0" to Ocean0, "1" to Ocean1, "2" to Ocean2, "3" to Ocean3,
                "4" to Ocean4, "5" to Ocean5, "6" to Ocean6, "7" to Ocean7,
                "8" to Ocean8, "9" to Ocean9, "10" to Ocean10, "11" to Ocean11,
            )
        )
        Spacer(Modifier.height(24.dp))

        SectionSubtitle("Blue (Tertiary Accent)")
        ColorRampRow(
            listOf(
                "0" to Blue0, "1" to Blue1, "2" to Blue2, "3" to Blue3,
                "4" to Blue4, "5" to Blue5, "6" to Blue6, "7" to Blue7,
                "8" to Blue8, "9" to Blue9, "10" to Blue10,
            )
        )
        Spacer(Modifier.height(24.dp))

        SectionSubtitle("Neutral (Grayscale)")
        ColorRampRow(
            listOf(
                "0" to Neutral0, "1" to Neutral1, "2" to Neutral2, "3" to Neutral3,
                "4" to Neutral4, "5" to Neutral5, "6" to Neutral6, "7" to Neutral7,
                "8" to Neutral8, "9" to Neutral9,
            )
        )
        Spacer(Modifier.height(24.dp))

        SectionSubtitle("Deep (Dark Surfaces)")
        ColorRampRow(
            listOf(
                "0" to Deep0,
                "1" to Deep1,
                "2" to Deep2,
                "3" to Deep3,
                "4" to Deep4,
                "5" to Deep5,
                "6" to Deep6,
                "7" to Deep7,
            )
        )
        Spacer(Modifier.height(24.dp))

        SectionSubtitle("SlateBlue (Cool Steel — text on dark)")
        ColorRampRow(
            listOf(
                "3" to SlateBlue3,
                "5" to SlateBlue5,
                "6" to SlateBlue6,
                "7" to SlateBlue7,
            )
        )
        Spacer(Modifier.height(24.dp))

        SectionSubtitle("Accent Tints")
        ColorRampRow(
            listOf(
                "ice" to IceLavender,
                "amberBright" to FunctionalAmberBright,
                "amberDeep" to FunctionalAmberDeep,
                "redBright" to FunctionalRedBright,
                "redDeep" to FunctionalRedDeep,
                "greenBright" to FunctionalGreenBright,
                "rust" to RustDeep,
                "emerald" to EmeraldDeep,
            )
        )
        SectionDivider()

        // ── 2. Semantic Colors ──────────────────────────────
        SectionTitle("2. Semantic Colors")

        SectionSubtitle("Surfaces")
        SemanticColorRow(
            listOf(
                "background" to colors.background,
                "surfaceElevated" to colors.surfaceElevated,
                "surfaceSunken" to colors.surfaceSunken,
                "surfaceNav" to colors.surfaceNav,
                "surfaceSection" to colors.surfaceSection,
                "surfaceContainerHigh" to colors.surfaceContainerHigh,
            )
        )
        Spacer(Modifier.height(20.dp))

        SectionSubtitle("Text")
        SemanticColorRow(
            listOf(
                "textPrimary" to colors.textPrimary,
                "textSecondary" to colors.textSecondary,
                "textMuted" to colors.textMuted,
                "textInverse" to colors.textInverse,
                "textLink" to colors.textLink,
            )
        )
        Spacer(Modifier.height(20.dp))

        SectionSubtitle("Brand")
        SemanticColorRow(
            listOf(
                "brand" to colors.brand,
                "brandSecondary" to colors.brandSecondary,
            )
        )
        Spacer(Modifier.height(20.dp))

        SectionSubtitle("Interactive")
        SemanticColorRow(
            listOf(
                "secondaryFill" to colors.interactiveSecondaryFill,
                "ghostHoverFill" to colors.interactiveGhostHoverFill,
            )
        )
        Spacer(Modifier.height(20.dp))

        SectionSubtitle("Code")
        SemanticColorRow(
            listOf(
                "codeBackground" to colors.codeBackground,
                "codeHeader" to colors.codeHeader,
            )
        )
        SectionDivider()

        // ── 3. Functional / Status Colors ───────────────────
        SectionTitle("3. Status Colors")
        StatusColorSection()
        SectionDivider()

        // ── 4. Syntax Highlighting ──────────────────────────
        SectionTitle("4. Syntax Highlighting")
        SyntaxColorSection()
        SectionDivider()

        // ── 5. Gradients ────────────────────────────────────
        SectionTitle("5. Gradients")
        GradientSection()
        SectionDivider()

        // ── 6. Typography ───────────────────────────────────
        SectionTitle("6. Typography")
        TypographySection()
        SectionDivider()

        // ── 7. Shapes ───────────────────────────────────────
        SectionTitle("7. Shapes")
        ShapesSection()
        SectionDivider()

        // ── 8. Spacing ──────────────────────────────────────
        SectionTitle("8. Spacing Scale")
        SpacingSection()
        SectionDivider()

        // ── 9. Tags ─────────────────────────────────────────
        SectionTitle("9. Tags")
        TagsSection()
        SectionDivider()

        // ── 10. Buttons ─────────────────────────────────────
        SectionTitle("10. Buttons")
        ButtonsSection()
        SectionDivider()

        // ── 11. Cards ───────────────────────────────────────
        SectionTitle("11. Cards & Surfaces")
        CardsSection()
        SectionDivider()

        // ── 12. Code Block ──────────────────────────────────
        SectionTitle("12. Code Block")
        CodeBlockSection()

        Spacer(Modifier.height(80.dp))
    }
}

// ─── Page Header ────────────────────────────────────────────

@Composable
private fun PageHeader() {
    val colors = BlogTheme.colors

    Column(modifier = Modifier.padding(bottom = 32.dp)) {
        Text(
            text = "DESIGN SYSTEM",
            style = MaterialTheme.typography.labelMedium,
            color = colors.brand,
            letterSpacing = 4.sp,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Blog Design Tokens",
            style = MaterialTheme.typography.displayLarge,
            color = colors.textPrimary,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = if (colors.isDark) "Dark Theme — The Luminous Curator" else "Light Theme — Clean Editorial",
            style = MaterialTheme.typography.bodyLarge,
            color = colors.textSecondary,
        )
    }
}

// ─── Section Helpers ────────────────────────────────────────

@Composable
private fun SectionTitle(title: String) {
    val colors = BlogTheme.colors
    Spacer(Modifier.height(8.dp))
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.labelMedium,
        color = colors.brand,
        letterSpacing = 3.sp,
    )
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun SectionSubtitle(subtitle: String) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.headlineMedium,
        color = BlogTheme.colors.textPrimary,
    )
    Spacer(Modifier.height(12.dp))
}

@Composable
private fun SectionDivider() {
    Spacer(Modifier.height(32.dp))
    HorizontalDivider(
        color = BlogTheme.colors.dividerGradientColor.copy(
            alpha = BlogTheme.colors.dividerGradientOpacity
        )
    )
    Spacer(Modifier.height(32.dp))
}

// ─── Color Ramp ─────────────────────────────────────────────

@Composable
private fun ColorRampRow(colors: List<Pair<String, Color>>) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        colors.forEach { (label, color) ->
            ColorSwatch(label = label, color = color, size = 80.dp)
        }
    }
}

@Composable
private fun ColorSwatch(label: String, color: Color, size: Dp) {
    val textColor = if (colorLuminance(color) > 0.5f) Color.Black else Color.White

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(10.dp))
                .background(color)
                .border(1.dp, BlogTheme.colors.textMuted.copy(alpha = 0.2f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = textColor,
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = colorToHex(color),
            style = MaterialTheme.typography.labelSmall,
            color = BlogTheme.colors.textMuted,
            fontSize = 9.sp,
        )
    }
}

// ─── Semantic Color Row ─────────────────────────────────────

@Composable
private fun SemanticColorRow(items: List<Pair<String, Color>>) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items.forEach { (name, color) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(1.dp, BlogTheme.colors.textMuted.copy(alpha = 0.2f), CircleShape),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodySmall,
                    color = BlogTheme.colors.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(80.dp),
                )
                Text(
                    text = colorToHex(color),
                    style = MaterialTheme.typography.labelSmall,
                    color = BlogTheme.colors.textMuted,
                    fontSize = 9.sp,
                )
            }
        }
    }
}

// ─── Status Colors ──────────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StatusColorSection() {
    val colors = BlogTheme.colors

    data class StatusGroup(
        val name: String,
        val accent: Color,
        val background: Color,
        val text: Color,
    )

    val groups = listOf(
        StatusGroup("Info", colors.statusInfoAccent, colors.statusInfoBackground, colors.statusInfoText),
        StatusGroup("Success", colors.statusSuccessAccent, colors.statusSuccessBackground, colors.statusSuccessText),
        StatusGroup("Warning", colors.statusWarningAccent, colors.statusWarningBackground, colors.statusWarningText),
        StatusGroup("Danger", colors.statusDangerAccent, colors.statusDangerBackground, colors.statusDangerText),
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        groups.forEach { group ->
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(group.background)
                    .border(1.dp, group.accent.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                    .padding(20.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(group.accent)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = group.name,
                            style = MaterialTheme.typography.titleSmall,
                            color = group.accent,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "This is a ${group.name.lowercase()} alert message.",
                            style = MaterialTheme.typography.bodySmall,
                            color = group.text,
                        )
                    }
                }
            }
        }
    }
}

// ─── Syntax Highlighting ────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SyntaxColorSection() {
    val tokens = listOf(
        "keyword" to SyntaxKeyword,
        "function" to SyntaxFunction,
        "type" to SyntaxType,
        "string" to SyntaxString,
        "error" to SyntaxError,
        "default" to SyntaxDefault,
        "comment" to SyntaxComment,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(BlogTheme.colors.codeBackground)
            .padding(24.dp)
    ) {
        Column {
            Text(
                text = "Syntax Tokens",
                style = MaterialTheme.typography.titleSmall,
                color = SyntaxDefault,
            )
            Spacer(Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                tokens.forEach { (name, color) ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(color)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = name,
                            style = CodeTextStyle,
                            color = color,
                        )
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            // Sample code
            Text(text = "// Sample code", style = CodeTextStyle, color = SyntaxComment)
            Row {
                Text(text = "fun ", style = CodeTextStyle, color = SyntaxKeyword)
                Text(text = "greet", style = CodeTextStyle, color = SyntaxFunction)
                Text(text = "(name: ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "String", style = CodeTextStyle, color = SyntaxType)
                Text(text = "): ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "String", style = CodeTextStyle, color = SyntaxType)
                Text(text = " {", style = CodeTextStyle, color = SyntaxDefault)
            }
            Row {
                Text(text = "    ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "return ", style = CodeTextStyle, color = SyntaxKeyword)
                Text(text = "\"Hello, \$name!\"", style = CodeTextStyle, color = SyntaxString)
            }
            Text(text = "}", style = CodeTextStyle, color = SyntaxDefault)
        }
    }
}

// ─── Gradients ──────────────────────────────────────────────

@Composable
private fun GradientSection() {
    val colors = BlogTheme.colors

    data class GradientItem(val name: String, val brush: Brush, val description: String)

    val items = listOf(
        GradientItem("Brand Gradient", BlogTheme.gradients.brand, "CTA buttons, hero accents"),
        GradientItem("Hero Gradient", BlogTheme.gradients.hero, "Display text, 3-stop"),
        GradientItem("Brand Subtle", BlogTheme.gradients.brandSubtle, "Section backgrounds"),
        GradientItem("Card Thumbnail", BlogTheme.gradients.cardThumbnail, "Card placeholders"),
        GradientItem("Nav Divider", BlogTheme.gradients.navDivider, "Navigation dividers"),
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .width(320.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(item.brush)
                        .border(1.dp, colors.textMuted.copy(alpha = 0.15f), RoundedCornerShape(10.dp)),
                )
                Spacer(Modifier.width(20.dp))
                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = colors.textPrimary,
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.textMuted,
                    )
                }
            }
        }
    }
}

// ─── Typography ─────────────────────────────────────────────

@Composable
private fun TypographySection() {
    val colors = BlogTheme.colors
    val typography = MaterialTheme.typography

    data class TypeSample(val name: String, val style: TextStyle, val sample: String)

    val samples = listOf(
        TypeSample("displayLarge (48sp)", typography.displayLarge, "Hero Headline"),
        TypeSample("displayMedium (40sp)", typography.displayMedium, "Page Title"),
        TypeSample("displaySmall (32sp)", typography.displaySmall, "Section Heading"),
        TypeSample("headlineLarge (28sp)", typography.headlineLarge, "Article H2"),
        TypeSample("headlineMedium (20sp)", typography.headlineMedium, "Card Title"),
        TypeSample("headlineSmall (18sp)", typography.headlineSmall, "Subsection Title"),
        TypeSample("titleLarge (22sp)", typography.titleLarge, "Nav Logo Title"),
        TypeSample("titleMedium (16sp)", typography.titleMedium, "Callout Title"),
        TypeSample("titleSmall (14sp)", typography.titleSmall, "Tag Label"),
        TypeSample("bodyLarge (17sp)", typography.bodyLarge, "Article body text for reading long-form content."),
        TypeSample("bodyMedium (15sp)", typography.bodyMedium, "Descriptions and secondary content."),
        TypeSample("bodySmall (13sp)", typography.bodySmall, "Captions and helper text."),
        TypeSample("labelLarge (15sp)", typography.labelLarge, "NAV LINK"),
        TypeSample("labelMedium (12sp)", typography.labelMedium, "BADGE TAG"),
        TypeSample("labelSmall (11sp)", typography.labelSmall, "META INFO"),
    )

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        samples.forEach { (name, style, sample) ->
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.textMuted,
                    modifier = Modifier.width(200.dp),
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = sample,
                    style = style,
                    color = colors.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

    Spacer(Modifier.height(24.dp))
    SectionSubtitle("Code Typography")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(colors.codeBackground)
            .padding(20.dp)
    ) {
        Text(
            text = "val parser = MarkdownParser(processors)",
            style = CodeTextStyle,
            color = SyntaxDefault,
        )
    }

    Spacer(Modifier.height(16.dp))
    SectionSubtitle("Blockquote Typography")
    Row(modifier = Modifier.padding(start = 16.dp)) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(60.dp)
                .background(colors.brand)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "The best code is the code you never have to write.",
            style = BlockquoteTextStyle,
            color = colors.textMuted,
        )
    }
}

// ─── Shapes ─────────────────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ShapesSection() {
    val colors = BlogTheme.colors

    data class ShapeItem(val name: String, val radius: Dp, val description: String)

    val items = listOf(
        ShapeItem("extraSmall", 6.dp, "Tags, badges"),
        ShapeItem("small", 10.dp, "Buttons, inputs"),
        ShapeItem("medium", 14.dp, "Cards, code blocks"),
        ShapeItem("large", 20.dp, "CTA sections"),
        ShapeItem("extraLarge", 24.dp, "Modals, drawers"),
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items.forEach { item ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(item.radius))
                        .background(colors.interactiveSecondaryFill)
                        .border(
                            1.dp,
                            colors.brand.copy(alpha = 0.3f),
                            RoundedCornerShape(item.radius)
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "${item.radius.value.toInt()}dp",
                        style = MaterialTheme.typography.titleSmall,
                        color = colors.textPrimary,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.textSecondary,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.textMuted,
                    fontSize = 10.sp,
                    letterSpacing = 0.sp,
                )
            }
        }

        // Pill shape
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(colors.interactiveSecondaryFill)
                    .border(1.dp, colors.brand.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "50%",
                    style = MaterialTheme.typography.titleSmall,
                    color = colors.textPrimary,
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "full",
                style = MaterialTheme.typography.bodySmall,
                color = colors.textSecondary,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = "Pill shape",
                style = MaterialTheme.typography.labelSmall,
                color = colors.textMuted,
                fontSize = 10.sp,
                letterSpacing = 0.sp,
            )
        }
    }
}

// ─── Spacing ────────────────────────────────────────────────

@Composable
private fun SpacingSection() {
    val colors = BlogTheme.colors
    val spacing = BlogSpacing()

    data class SpacingToken(val name: String, val value: Dp)

    val tokens = listOf(
        SpacingToken("xs", spacing.xs),
        SpacingToken("sm", spacing.sm),
        SpacingToken("md", spacing.md),
        SpacingToken("lg", spacing.lg),
        SpacingToken("xl", spacing.xl),
        SpacingToken("xxl", spacing.xxl),
        SpacingToken("xxxl", spacing.xxxl),
        SpacingToken("huge", spacing.huge),
        SpacingToken("massive", spacing.massive),
        SpacingToken("section", spacing.section),
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        tokens.forEach { token ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${token.name} (${token.value.value.toInt()}dp)",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.textMuted,
                    modifier = Modifier.width(140.dp),
                )
                Spacer(Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .width(token.value)
                        .height(16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            BlogTheme.gradients.brand
                        )
                )
            }
        }
    }
}

// ─── Tags ───────────────────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagsSection() {
    val colors = BlogTheme.colors

    data class TagItem(val label: String, val bg: Color, val text: Color)

    val tags = listOf(
        TagItem("TypeScript", colors.tagTypescriptBackground, colors.tagTypescriptText),
        TagItem("Rust", colors.tagRustBackground, colors.tagRustText),
        TagItem("React", colors.tagReactBackground, colors.tagReactText),
        TagItem("Go", colors.tagGoBackground, colors.tagGoText),
        TagItem("Default", colors.tagDefaultBackground, colors.tagDefaultText),
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        tags.forEach { tag ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(tag.bg)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
            ) {
                Text(
                    text = tag.label,
                    style = MaterialTheme.typography.labelMedium,
                    color = tag.text,
                    letterSpacing = 1.sp,
                )
            }
        }
    }
}

// ─── Buttons ────────────────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ButtonsSection() {
    val colors = BlogTheme.colors

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Primary button (gradient)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(BlogTheme.gradients.brand)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Primary Action",
                style = MaterialTheme.typography.labelLarge,
                color = colors.interactivePrimaryText,
                fontWeight = FontWeight.SemiBold,
            )
        }

        // Secondary button
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colors.interactiveSecondaryFill)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Secondary",
                style = MaterialTheme.typography.labelLarge,
                color = colors.interactiveSecondaryText,
                fontWeight = FontWeight.SemiBold,
            )
        }

        // Ghost button
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, colors.brand.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Ghost",
                style = MaterialTheme.typography.labelLarge,
                color = colors.interactiveGhostText,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

// ─── Cards ──────────────────────────────────────────────────

@Composable
private fun CardsSection() {
    val colors = BlogTheme.colors

    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.horizontalScroll(rememberScrollState()),
    ) {
        // Elevated card
        Box(
            modifier = Modifier
                .width(320.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(colors.surfaceElevated)
                .border(1.dp, colors.textMuted.copy(alpha = 0.1f), RoundedCornerShape(14.dp))
                .padding(24.dp),
        ) {
            Column {
                // Thumbnail placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(BlogTheme.gradients.cardThumbnail)
                )
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(colors.tagTypescriptBackground)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = "TypeScript",
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.tagTypescriptText,
                            letterSpacing = 0.5.sp,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(colors.tagReactBackground)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = "React",
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.tagReactText,
                            letterSpacing = 0.5.sp,
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Building a Design System",
                    style = MaterialTheme.typography.headlineMedium,
                    color = colors.textPrimary,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "How to create a scalable, themeable design system for your Compose Multiplatform app.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "5 min read",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.textMuted,
                    letterSpacing = 0.5.sp,
                )
            }
        }

        // Brand subtle card
        Box(
            modifier = Modifier
                .width(320.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(BlogTheme.gradients.brandSubtle)
                .border(1.dp, colors.brand.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
                .padding(32.dp),
        ) {
            Column {
                Text(
                    text = "Newsletter",
                    style = MaterialTheme.typography.headlineMedium,
                    color = colors.textPrimary,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Subscribe to get the latest posts delivered to your inbox.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary,
                )
                Spacer(Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(BlogTheme.gradients.brand)
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Subscribe",
                        style = MaterialTheme.typography.labelLarge,
                        color = colors.interactivePrimaryText,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}

// ─── Code Block ─────────────────────────────────────────────

@Composable
private fun CodeBlockSection() {
    val colors = BlogTheme.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(colors.codeBackground),
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.codeHeader)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Theme.kt",
                style = CodeTextStyle,
                color = SyntaxDefault,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "kotlin",
                style = MaterialTheme.typography.labelSmall,
                color = SyntaxComment,
                letterSpacing = 0.5.sp,
            )
        }
        // Code body
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "// Blog Theme Composable", style = CodeTextStyle, color = SyntaxComment)
            Spacer(Modifier.height(4.dp))
            Row {
                Text(text = "@Composable", style = CodeTextStyle, color = SyntaxKeyword)
            }
            Row {
                Text(text = "fun ", style = CodeTextStyle, color = SyntaxKeyword)
                Text(text = "BlogTheme", style = CodeTextStyle, color = SyntaxFunction)
                Text(text = "(", style = CodeTextStyle, color = SyntaxDefault)
            }
            Row {
                Text(text = "    darkTheme: ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "Boolean", style = CodeTextStyle, color = SyntaxType)
                Text(text = ",", style = CodeTextStyle, color = SyntaxDefault)
            }
            Row {
                Text(text = "    content: ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "@Composable ", style = CodeTextStyle, color = SyntaxKeyword)
                Text(text = "() -> ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "Unit", style = CodeTextStyle, color = SyntaxType)
            }
            Row {
                Text(text = ") {", style = CodeTextStyle, color = SyntaxDefault)
            }
            Row {
                Text(text = "    ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "val ", style = CodeTextStyle, color = SyntaxKeyword)
                Text(text = "colors = ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "if ", style = CodeTextStyle, color = SyntaxKeyword)
                Text(text = "(darkTheme) DarkColorPalette", style = CodeTextStyle, color = SyntaxDefault)
            }
            Row {
                Text(text = "    ", style = CodeTextStyle, color = SyntaxDefault)
                Text(text = "else ", style = CodeTextStyle, color = SyntaxKeyword)
                Text(text = "LightColorPalette", style = CodeTextStyle, color = SyntaxDefault)
            }
            Text(text = "}", style = CodeTextStyle, color = SyntaxDefault)
        }
    }
}

// ─── Utilities ──────────────────────────────────────────────

private fun colorToHex(color: Color): String {
    val r = (color.red * 255).toInt()
    val g = (color.green * 255).toInt()
    val b = (color.blue * 255).toInt()
    return "#${r.toHexByte()}${g.toHexByte()}${b.toHexByte()}"
}

private fun Int.toHexByte(): String {
    val hex = "0123456789ABCDEF"
    return "${hex[(this shr 4) and 0xF]}${hex[this and 0xF]}"
}

private fun colorLuminance(color: Color): Float {
    return 0.299f * color.red + 0.587f * color.green + 0.114f * color.blue
}
