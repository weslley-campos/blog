package br.com.weslleycampos.blog.feature.about.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.components.SectionTitle
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_experience
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.feature.about.data.Experience
import br.com.weslleycampos.blog.feature.about.data.experiences
import org.jetbrains.compose.resources.stringResource

/**
 * Vertical experience timeline. Each entry pairs a brand-gradient marker
 * with a content column showing period → company → description. The rail
 * column uses `IntrinsicSize.Min` + `weight(1f)` on the connector so the
 * line stretches between markers regardless of description length.
 */
@Composable
internal fun AboutTimeline(
    modifier: Modifier = Modifier,
    items: List<Experience> = experiences,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xxxl),
    ) {
        SectionTitle(text = stringResource(CoreUiRes.string.about_experience))
        Column {
            items.forEachIndexed { index, experience ->
                TimelineEntry(
                    experience = experience,
                    isLast = index == items.lastIndex,
                )
            }
        }
    }
}

@Composable
private fun TimelineEntry(experience: Experience, isLast: Boolean) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        TimelineRail(isLast = isLast)
        Spacer(modifier = Modifier.width(BlogTheme.spacing.xl))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = if (isLast) 0.dp else BlogTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.sm),
        ) {
            Text(
                text = experience.period,
                style = BlogTheme.typography.labelMedium,
                color = BlogTheme.colors.brand,
            )
            Text(
                text = experience.company,
                style = BlogTheme.typography.headlineMedium,
                color = BlogTheme.colors.textPrimary,
            )
            Text(
                text = experience.description,
                style = BlogTheme.typography.bodyMedium,
                color = BlogTheme.colors.textSecondary,
            )
        }
    }
}

@Composable
private fun TimelineRail(isLast: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(),
    ) {
        Box(
            modifier = Modifier
                .size(MARKER_SIZE)
                .clip(CircleShape)
                .background(BlogTheme.gradients.brand),
        )
        if (!isLast) {
            Box(
                modifier = Modifier
                    .width(RAIL_WIDTH)
                    .weight(1f)
                    .background(BlogTheme.colors.brand.copy(alpha = RAIL_ALPHA)),
            )
        }
    }
}

private val MARKER_SIZE = 14.dp
private val RAIL_WIDTH = 2.dp
private const val RAIL_ALPHA = 0.30f

@Preview(widthDp = 400, heightDp = 1200, showBackground = true)
@Composable
private fun AboutTimelinePreviewDark() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutTimeline()
        }
    }
}

@Preview(widthDp = 400, heightDp = 1200, showBackground = true)
@Composable
private fun AboutTimelinePreviewLight() {
    BlogTheme(isDarkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutTimeline()
        }
    }
}
