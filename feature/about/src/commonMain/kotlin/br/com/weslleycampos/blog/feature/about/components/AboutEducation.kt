package br.com.weslleycampos.blog.feature.about.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.weslleycampos.blog.core.ui.components.SectionTitle
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_education
import br.com.weslleycampos.blog.core.ui.resources.about_education_degree
import br.com.weslleycampos.blog.core.ui.resources.about_education_description
import br.com.weslleycampos.blog.core.ui.resources.about_education_school
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import org.jetbrains.compose.resources.stringResource

/**
 * Education section — single résumé entry rendered as `// Education` eyebrow
 * followed by school → degree+dates → short description. Layout mirrors the
 * inner shape of one [AboutTimeline] entry minus the rail, since there's
 * only one institution to surface.
 */
@Composable
internal fun AboutEducation(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xxxl),
    ) {
        SectionTitle(text = stringResource(CoreUiRes.string.about_education))
        Column(verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.sm)) {
            Text(
                text = stringResource(CoreUiRes.string.about_education_school),
                style = BlogTheme.typography.headlineMedium,
                color = BlogTheme.colors.textPrimary,
            )
            Text(
                text = stringResource(CoreUiRes.string.about_education_degree),
                style = BlogTheme.typography.labelMedium,
                color = BlogTheme.colors.brand,
            )
            Text(
                text = stringResource(CoreUiRes.string.about_education_description),
                style = BlogTheme.typography.bodyMedium,
                color = BlogTheme.colors.textSecondary,
            )
        }
    }
}

@Preview(widthDp = 400, heightDp = 360, showBackground = true)
@Composable
private fun AboutEducationPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutEducation()
        }
    }
}

@Preview(widthDp = 400, heightDp = 360, showBackground = true)
@Composable
private fun AboutEducationPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutEducation()
        }
    }
}
