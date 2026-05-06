package br.com.weslleycampos.blog.feature.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

@Composable
internal fun FormErrorBanner(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = message,
        modifier = modifier
            .fillMaxWidth()
            .clip(BlogTheme.shapes.small)
            .background(color = BlogTheme.colors.statusDangerBackground)
            .padding(horizontal = BlogTheme.spacing.lg, vertical = BlogTheme.spacing.md)
            .semantics { liveRegion = LiveRegionMode.Polite },
        style = BlogTheme.typography.bodySmall,
        color = BlogTheme.colors.statusDangerText,
    )
}
