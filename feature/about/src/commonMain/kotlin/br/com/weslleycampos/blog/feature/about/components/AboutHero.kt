package br.com.weslleycampos.blog.feature.about.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.components.BlogPrimaryButton
import br.com.weslleycampos.blog.core.ui.components.GradientText
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_bio_paragraph
import br.com.weslleycampos.blog.core.ui.resources.about_cta_resume
import br.com.weslleycampos.blog.core.ui.resources.about_eyebrow_biography
import br.com.weslleycampos.blog.core.ui.resources.about_hero_line_one
import br.com.weslleycampos.blog.core.ui.resources.about_hero_line_two
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import org.jetbrains.compose.resources.stringResource

/**
 * Top-of-page hero — gradient avatar placeholder, biography eyebrow,
 * two-line gradient headline, bio paragraph, résumé CTA.
 *
 * `bodyMaxWidth` caps the prose column so longer breakpoints don't stretch
 * the body copy past comfortable reading width. Default `Dp.Unspecified`
 * lets compact layouts use the full container.
 */
@Composable
internal fun AboutHero(
    onResumeClick: () -> Unit,
    modifier: Modifier = Modifier,
    avatarSize: Dp = HERO_AVATAR_SIZE,
    bodyMaxWidth: Dp = Dp.Unspecified,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xxl),
    ) {
        Box(
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape)
                .background(BlogTheme.gradients.brand),
        )

        Column(verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.sm)) {
            Text(
                text = stringResource(CoreUiRes.string.about_eyebrow_biography),
                style = BlogTheme.typography.labelMedium,
                color = BlogTheme.colors.brand,
            )
            GradientText(
                text = stringResource(CoreUiRes.string.about_hero_line_one),
                style = BlogTheme.typography.displayMedium,
            )
            GradientText(
                text = stringResource(CoreUiRes.string.about_hero_line_two),
                style = BlogTheme.typography.displayMedium,
            )
        }

        Text(
            text = stringResource(CoreUiRes.string.about_bio_paragraph),
            style = BlogTheme.typography.bodyLarge,
            color = BlogTheme.colors.textSecondary,
            modifier = Modifier.widthIn(max = bodyMaxWidth),
        )

        BlogPrimaryButton(
            text = stringResource(CoreUiRes.string.about_cta_resume),
            onClick = onResumeClick,
        )
    }
}

private val HERO_AVATAR_SIZE = 120.dp

@Preview(widthDp = 400, heightDp = 720, showBackground = true)
@Composable
private fun AboutHeroPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutHero(onResumeClick = {})
        }
    }
}

@Preview(widthDp = 400, heightDp = 720, showBackground = true)
@Composable
private fun AboutHeroPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutHero(onResumeClick = {})
        }
    }
}
