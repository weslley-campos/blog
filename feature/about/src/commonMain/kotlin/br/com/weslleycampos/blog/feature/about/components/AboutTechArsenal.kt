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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.components.SectionEyebrow
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_eyebrow_arsenal
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.feature.about.data.TechItem
import br.com.weslleycampos.blog.feature.about.data.techArsenal
import org.jetbrains.compose.resources.stringResource

/**
 * Tech Arsenal section — section header above a `FlowRow` of circle badges.
 * Each badge renders the tech initials inside a `surfaceContainerHigh`
 * circle bordered with a faint brand stroke; label sits below.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun AboutTechArsenal(
    modifier: Modifier = Modifier,
    items: List<TechItem> = techArsenal,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xxxl),
    ) {
        SectionEyebrow(text = stringResource(CoreUiRes.string.about_eyebrow_arsenal))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xl),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items.forEach { item ->
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
            Text(
                text = item.initials,
                style = BlogTheme.typography.labelMedium,
                color = BlogTheme.colors.brand,
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
private const val BADGE_BORDER_ALPHA = 0.30f

@Preview(widthDp = 400, heightDp = 520, showBackground = true)
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

@Preview(widthDp = 400, heightDp = 520, showBackground = true)
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
