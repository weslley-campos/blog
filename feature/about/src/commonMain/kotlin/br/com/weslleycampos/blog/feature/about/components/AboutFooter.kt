package br.com.weslleycampos.blog.feature.about.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_footer_copyright
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.painter
import br.com.weslleycampos.blog.feature.about.data.SocialLink
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource

/**
 * Footer band — social links row above the copyright line. `onLinkClick`
 * surfaces the chosen `SocialLink` so the screen-level wiring decides how
 * to open URLs per platform (Wasm tab, Desktop browser, Android intent).
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun AboutFooter(
    onLinkClick: (SocialLink) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg),
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xl, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.sm),
        ) {
            SocialLink.entries.forEach { link ->
                SocialLinkItem(link = link, onClick = { onLinkClick(link) })
            }
        }
        Text(
            text = stringResource(CoreUiRes.string.about_footer_copyright),
            style = BlogTheme.typography.bodySmall,
            color = BlogTheme.colors.textMuted,
        )
    }
}

@Composable
private fun SocialLinkItem(link: SocialLink, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(BlogTheme.shapes.small)
            .clickable(onClick = onClick)
            .padding(horizontal = BlogTheme.spacing.sm, vertical = BlogTheme.spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(BlogTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = iconFor(link).painter,
            contentDescription = link.label,
            tint = BlogTheme.colors.textSecondary,
            modifier = Modifier.size(BlogTheme.sizes.icon.small),
        )
        Text(
            text = link.label,
            style = BlogTheme.typography.bodyMedium,
            color = BlogTheme.colors.textSecondary,
        )
    }
}

@Composable
private fun iconFor(link: SocialLink): DrawableResource = when (link) {
    SocialLink.Github -> BlogTheme.icons.Github
    SocialLink.Linkedin -> BlogTheme.icons.Linkedin
    SocialLink.Email -> BlogTheme.icons.Mail
}

@Preview(widthDp = 400, heightDp = 200, showBackground = true)
@Composable
private fun AboutFooterPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutFooter(onLinkClick = {})
        }
    }
}

@Preview(widthDp = 400, heightDp = 200, showBackground = true)
@Composable
private fun AboutFooterPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutFooter(onLinkClick = {})
        }
    }
}
