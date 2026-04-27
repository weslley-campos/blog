package br.com.weslleycampos.blog.feature.about.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.components.BlogPrimaryButton
import br.com.weslleycampos.blog.core.ui.components.BlogSecondaryButton
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_cta_email
import br.com.weslleycampos.blog.core.ui.resources.about_cta_headline
import br.com.weslleycampos.blog.core.ui.resources.about_cta_hire
import br.com.weslleycampos.blog.core.ui.resources.about_cta_subtitle
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import org.jetbrains.compose.resources.stringResource

/**
 * Closing CTA — gradient brand-subtle card with headline, subtitle, and a
 * two-button cluster (`Hire for Project` primary, `Send Email` secondary).
 * Uses `FlowRow` so the buttons wrap onto a second line on narrow widths.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun AboutCtaCard(
    onHireClick: () -> Unit,
    onEmailClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(BlogTheme.shapes.large)
            .background(BlogTheme.gradients.brandSubtle)
            .border(
                width = 1.dp,
                color = BlogTheme.colors.brand.copy(alpha = CTA_BORDER_ALPHA),
                shape = BlogTheme.shapes.large,
            )
            .padding(BlogTheme.spacing.xxxl),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg)) {
            Text(
                text = stringResource(CoreUiRes.string.about_cta_headline),
                style = BlogTheme.typography.displaySmall,
                color = BlogTheme.colors.textPrimary,
            )
            Text(
                text = stringResource(CoreUiRes.string.about_cta_subtitle),
                style = BlogTheme.typography.bodyLarge,
                color = BlogTheme.colors.textSecondary,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.md),
            ) {
                BlogPrimaryButton(
                    text = stringResource(CoreUiRes.string.about_cta_hire),
                    onClick = onHireClick,
                )
                BlogSecondaryButton(
                    text = stringResource(CoreUiRes.string.about_cta_email),
                    onClick = onEmailClick,
                )
            }
        }
    }
}

private const val CTA_BORDER_ALPHA = 0.10f

@Preview(widthDp = 400, heightDp = 420, showBackground = true)
@Composable
private fun AboutCtaCardPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutCtaCard(onHireClick = {}, onEmailClick = {})
        }
    }
}

@Preview(widthDp = 400, heightDp = 420, showBackground = true)
@Composable
private fun AboutCtaCardPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutCtaCard(onHireClick = {}, onEmailClick = {})
        }
    }
}
