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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

/**
 * Code-comment style eyebrow — `// {text}` rendered in JetBrainsMono Medium
 * (`labelMedium`) on `colors.brandSecondary`. The `// ` prefix is added by the
 * component so callers pass a clean label and the marker stays consistent.
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
 * Section header — code-comment eyebrow above a display heading.
 *
 * Used by every section in About + likely Posts later. Eyebrow uses
 * [SectionEyebrow] (`// {label}` in mono cyan); title defaults to
 * `displaySmall` on `textPrimary`.
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

@Preview(widthDp = 600, heightDp = 240, showBackground = true)
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
            SectionHeader(
                eyebrow = "tech arsenal",
                title = "Tech Arsenal",
            )
            SectionEyebrow(text = "standalone eyebrow")
        }
    }
}

@Preview(widthDp = 600, heightDp = 240, showBackground = true)
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
            SectionHeader(
                eyebrow = "the professional journey",
                title = "Experience Timeline",
            )
            SectionEyebrow(text = "standalone eyebrow")
        }
    }
}
