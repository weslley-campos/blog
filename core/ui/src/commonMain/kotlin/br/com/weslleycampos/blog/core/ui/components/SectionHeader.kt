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
 * Section header — eyebrow (small caps, tracked) over a display heading.
 *
 * Used on every section in About + Posts. Eyebrow uses `labelMedium` (mono,
 * tracked) on `colors.brand`; title uses `displaySmall` on `colors.textPrimary`.
 */
@Composable
fun SectionHeader(
    eyebrow: String,
    title: String,
    modifier: Modifier = Modifier,
    eyebrowStyle: TextStyle = BlogTheme.typography.labelMedium,
    titleStyle: TextStyle = BlogTheme.typography.displaySmall,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.md),
    ) {
        Text(
            text = eyebrow,
            style = eyebrowStyle,
            color = BlogTheme.colors.brand,
        )
        Text(
            text = title,
            style = titleStyle,
            color = BlogTheme.colors.textPrimary,
        )
    }
}

@Preview(widthDp = 600, heightDp = 200, showBackground = true)
@Composable
private fun SectionHeaderPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(32.dp),
        ) {
            SectionHeader(
                eyebrow = "BIOGRAPHY",
                title = "Crafting Digital Craft",
            )
        }
    }
}

@Preview(widthDp = 600, heightDp = 200, showBackground = true)
@Composable
private fun SectionHeaderPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(32.dp),
        ) {
            SectionHeader(
                eyebrow = "TECH ARSENAL",
                title = "Tech Arsenal",
            )
        }
    }
}
