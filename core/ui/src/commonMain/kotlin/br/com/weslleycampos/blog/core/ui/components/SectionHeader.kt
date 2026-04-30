package br.com.weslleycampos.blog.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.JetBrainsMonoFontFamily

/**
 * Small code-comment eyebrow — `// {text}` in JetBrainsMono Medium 12sp on
 * `colors.brandSecondary`. Use when the marker sits *above* a bigger headline
 * (e.g. the hero pairs `// About me` with "Hi, I'm Weslley.").
 */
@Composable
fun SectionEyebrow(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "// $text",
        style = BlogTheme.typography.labelMedium,
        color = BlogTheme.colors.brandSecondary,
        modifier = modifier,
    )
}

/**
 * Bigger code-comment section title — `// {text}` in JetBrainsMono Medium 22sp
 * on `colors.brandSecondary`. Use when the marker stands alone as the section's
 * heading (Tech Arsenal, Experience, Education).
 */
@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "// $text",
        style = TextStyle(
            fontFamily = JetBrainsMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 30.sp,
            letterSpacing = 0.sp,
        ),
        color = BlogTheme.colors.brandSecondary,
        modifier = modifier,
    )
}

/**
 * Section header — small eyebrow above a display heading. Kept around for
 * sections that genuinely pair the `//` marker with a distinct headline (the
 * hero pattern) instead of using the marker as the section title itself.
 */
@Composable
fun SectionHeader(
    eyebrow: String,
    title: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = BlogTheme.typography.displaySmall,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.md),
    ) {
        SectionEyebrow(text = eyebrow)
        Text(
            text = title,
            style = titleStyle,
            color = BlogTheme.colors.textPrimary,
        )
    }
}

@Preview(widthDp = 600, heightDp = 320, showBackground = true)
@Composable
private fun SectionHeaderPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            SectionTitle(text = "Tech arsenal")
            SectionEyebrow(text = "About me")
            SectionHeader(eyebrow = "Skills", title = "Tech Arsenal")
        }
    }
}

@Preview(widthDp = 600, heightDp = 320, showBackground = true)
@Composable
private fun SectionHeaderPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            SectionTitle(text = "Experience")
            SectionEyebrow(text = "About me")
            SectionHeader(eyebrow = "Skills", title = "Tech Arsenal")
        }
    }
}
