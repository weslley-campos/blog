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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.components.SectionEyebrow
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_eyebrow_arsenal
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.painter
import br.com.weslleycampos.blog.feature.about.data.TechGroup
import br.com.weslleycampos.blog.feature.about.data.TechItem
import br.com.weslleycampos.blog.feature.about.data.techArsenal
import org.jetbrains.compose.resources.stringResource

/**
 * Tech Arsenal section — single eyebrow over a vertical stack of grouped
 * badge clusters. Each [TechGroup] renders a quiet sub-label in
 * `textSecondary` followed by its own `FlowRow` of badges, so wrapping
 * happens within a category instead of across mixed concepts.
 *
 * Badge styling is identical across groups (56 dp circle, brand-purple
 * ring, 28 dp glyph). Brand-color glyphs render in their native colors;
 * stroke glyphs bake `#7F52FF` into the AVD so `tint = Color.Unspecified`
 * works for every item with no per-item logic.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun AboutTechArsenal(
    modifier: Modifier = Modifier,
    groups: List<TechGroup> = techArsenal,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xxxl),
    ) {
        SectionEyebrow(text = stringResource(CoreUiRes.string.about_eyebrow_arsenal))
        Column(verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xxl)) {
            groups.forEach { group ->
                TechGroupBlock(group = group)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TechGroupBlock(group: TechGroup) {
    Column(verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg)) {
        Text(
            text = group.label,
            style = BlogTheme.typography.bodyMedium,
            color = BlogTheme.colors.textSecondary,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xl),
            modifier = Modifier.fillMaxWidth(),
        ) {
            group.items.forEach { item ->
                TechBadge(item = item)
            }
        }
    }
}

@Composable
private fun TechBadge(item: TechItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xs),
    ) {
        Box(
            modifier = Modifier
                .size(BADGE_SIZE)
                .clip(CircleShape)
                .background(BlogTheme.colors.surfaceContainerHigh)
                .border(
                    width = 1.dp,
                    color = BlogTheme.colors.brand.copy(alpha = BADGE_BORDER_ALPHA),
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = item.icon.painter,
                contentDescription = item.label,
                tint = Color.Unspecified,
                modifier = Modifier.size(BADGE_ICON_SIZE),
            )
        }
        Text(
            text = item.label,
            style = BlogTheme.typography.bodySmall,
            color = BlogTheme.colors.textSecondary,
        )
    }
}

private val BADGE_SIZE = 56.dp
private val BADGE_ICON_SIZE = 28.dp
private const val BADGE_BORDER_ALPHA = 0.30f

@Preview(widthDp = 400, heightDp = 1100, showBackground = true)
@Composable
private fun AboutTechArsenalPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutTechArsenal()
        }
    }
}

@Preview(widthDp = 400, heightDp = 1100, showBackground = true)
@Composable
private fun AboutTechArsenalPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutTechArsenal()
        }
    }
}
