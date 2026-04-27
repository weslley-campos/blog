package br.com.weslleycampos.blog.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

/**
 * Text rendered with a [Brush] applied via `TextStyle.brush`.
 *
 * Use for the hero headline, logo, or any display copy that should pick up the
 * brand gradient. Defaults to `BlogTheme.gradients.hero` and `displayLarge`.
 *
 * The base `style` is merged with `TextStyle(brush = brush)` so callers can
 * override font, weight, size, and alignment without losing the gradient.
 */
@Composable
fun GradientText(
    text: String,
    modifier: Modifier = Modifier,
    brush: Brush = BlogTheme.gradients.hero,
    style: TextStyle = BlogTheme.typography.displayLarge,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style.merge(TextStyle(brush = brush)),
        textAlign = textAlign,
        maxLines = maxLines,
    )
}

@Preview(widthDp = 700, heightDp = 280, showBackground = true)
@Composable
private fun GradientTextPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            GradientText(text = "Crafting Digital")
            GradientText(text = "Craft")
            GradientText(
                text = "brand gradient",
                brush = BlogTheme.gradients.brand,
                style = BlogTheme.typography.headlineLarge,
            )
        }
    }
}

@Preview(widthDp = 700, heightDp = 280, showBackground = true)
@Composable
private fun GradientTextPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            GradientText(text = "Crafting Digital")
            GradientText(text = "Craft")
            GradientText(
                text = "brand gradient",
                brush = BlogTheme.gradients.brand,
                style = BlogTheme.typography.headlineLarge,
            )
        }
    }
}
