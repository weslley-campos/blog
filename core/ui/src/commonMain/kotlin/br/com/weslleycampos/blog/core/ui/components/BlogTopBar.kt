package br.com.weslleycampos.blog.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.a11y_language_button
import br.com.weslleycampos.blog.core.ui.resources.a11y_menu_button
import br.com.weslleycampos.blog.core.ui.resources.a11y_theme_mode_button
import br.com.weslleycampos.blog.core.ui.resources.author_name
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.LocalDarkTheme
import br.com.weslleycampos.blog.core.ui.theme.MerriWeatherFontFamily
import br.com.weslleycampos.blog.core.ui.theme.painter
import br.com.weslleycampos.blog.core.ui.utils.LocalScreenSize
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize.Compact
import br.com.weslleycampos.blog.core.ui.utils.ScreenSizeParameterProvider
import org.jetbrains.compose.resources.stringResource

@Composable
fun BlogTopBar(
    onThemeToggle: (Boolean) -> Unit,
    onSelectLanguage: (Locale) -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentScreenSize = LocalScreenSize.current
    var selected by remember { mutableStateOf("Posts") }
    var showLanguageSelector by remember { mutableStateOf(false) }

    when (currentScreenSize) {
        Compact -> CompactTopBar(
            onMenuClick = { },
            onLanguageClick = { showLanguageSelector = true },
            onThemeToggle = onThemeToggle,
            modifier = modifier
        )

        else -> ExpandedTopBar(
            selected = selected,
            onItemClick = { selected = it },
            onThemeToggle = onThemeToggle,
            onLanguageClick = { showLanguageSelector = true },
            modifier = modifier
        )
    }

    if (showLanguageSelector) {
        SelectLanguageDialog(
            onDismiss = { showLanguageSelector = false },
            onSelected = {
                onSelectLanguage(Locale(languageTag = it))
            }
        )
    }
}

@Composable
private fun ExpandedTopBar(
    selected: String,
    onItemClick: (String) -> Unit,
    onThemeToggle: (Boolean) -> Unit,
    onLanguageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val maxSize = LocalScreenSize.current.size.dp

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(color = BlogTheme.colors.app.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = modifier.widthIn(max = maxSize)
                .height(BlogTheme.spacing.section)
                .padding(horizontal = BlogTheme.spacing.lg),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(CoreUiRes.string.author_name),
                style = BlogTheme.typography.titleMedium,
                fontFamily = MerriWeatherFontFamily,
                color = BlogTheme.colors.text.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            NavBarItem(
                icon = BlogTheme.icons.run {
                    if (selected == "Posts") Home else HomeOutline
                }.painter,
                label = "Posts",
                isSelected = selected == "Posts",
                onClick = { onItemClick("Posts") }
            )
            Spacer(modifier = Modifier.width(BlogTheme.spacing.xs))
            NavBarItem(
                icon = BlogTheme.icons.run {
                    if (selected == "About") User else UserOutline
                }.painter,
                label = "About",
                isSelected = selected == "About",
                onClick = { onItemClick("About") }
            )
            VerticalDivider(
                modifier = Modifier.height(BlogTheme.spacing.xl)
                    .padding(horizontal = BlogTheme.spacing.sm),
                color = BlogTheme.colors.border.divider
            )

            val isDarkThemeEnabled = LocalDarkTheme.current
            IconButton(onClick = { onThemeToggle.invoke(!isDarkThemeEnabled) }) {
                Icon(
                    painter = BlogTheme.icons.run {
                        if (isDarkThemeEnabled) Moon else Sun
                    }.painter,
                    contentDescription = stringResource(CoreUiRes.string.a11y_theme_mode_button),
                    tint = BlogTheme.colors.icon.onSurface,
                    modifier = Modifier.size(BlogTheme.sizes.icon.small)
                )
            }
            IconButton(onClick = onLanguageClick) {
                Icon(
                    painter = BlogTheme.icons.Translate.painter,
                    contentDescription = stringResource(CoreUiRes.string.a11y_language_button),
                    tint = BlogTheme.colors.icon.onSurface,
                    modifier = Modifier.size(BlogTheme.sizes.icon.small)
                )
            }
        }
        HorizontalDivider(color = BlogTheme.colors.border.divider)
    }
}

@Composable
private fun NavBarItem(
    icon: Painter,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clip(shape = BlogTheme.shapes.full)
            .clickable(onClick = onClick)
            .padding(horizontal = BlogTheme.spacing.md, vertical = BlogTheme.spacing.sm),
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            tint = BlogTheme.colors.icon.run {
                if (isSelected) primary else onSurface
            },
            modifier = Modifier.size(BlogTheme.sizes.icon.small)
        )
        Spacer(modifier = Modifier.width(BlogTheme.spacing.sm))
        Text(
            text = label,
            style = BlogTheme.typography.bodyMedium,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = BlogTheme.colors.text.run {
                if (isSelected) primary else onSurface
            },
        )
    }
}

@Composable
private fun CompactTopBar(
    onMenuClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onThemeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val maxSize = LocalScreenSize.current.size.dp

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(BlogTheme.colors.app.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = modifier.widthIn(max = maxSize)
                .height(BlogTheme.spacing.massive)
                .padding(horizontal = BlogTheme.spacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(CoreUiRes.string.author_name),
                style = BlogTheme.typography.titleSmall,
                fontFamily = MerriWeatherFontFamily,
                color = BlogTheme.colors.text.primary
            )
            Spacer(modifier = Modifier.weight(1f))

            val isDarkThemeEnabled = LocalDarkTheme.current
            IconButton(
                onClick = { onThemeToggle.invoke(!isDarkThemeEnabled) },
                modifier = Modifier.size(BlogTheme.sizes.icon.large)
            ) {
                Icon(
                    painter = BlogTheme.icons.run {
                        if (isDarkThemeEnabled) Moon else Sun
                    }.painter,
                    contentDescription = stringResource(CoreUiRes.string.a11y_theme_mode_button),
                    tint = BlogTheme.colors.icon.onSurface,
                    modifier = Modifier.size(BlogTheme.sizes.icon.small)
                )
            }
            IconButton(
                onClick = onLanguageClick,
                modifier = Modifier.size(BlogTheme.sizes.icon.large)
            ) {
                Icon(
                    painter = BlogTheme.icons.Translate.painter,
                    contentDescription = stringResource(CoreUiRes.string.a11y_language_button),
                    tint = BlogTheme.colors.icon.onSurface,
                    modifier = Modifier.size(BlogTheme.sizes.icon.small)
                )
            }
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(BlogTheme.sizes.icon.large)
            ) {
                Icon(
                    painter = BlogTheme.icons.Menu.painter,
                    contentDescription = stringResource(CoreUiRes.string.a11y_menu_button),
                    tint = BlogTheme.colors.icon.onSurface,
                    modifier = Modifier.size(BlogTheme.sizes.icon.small)
                )
            }
        }
        HorizontalDivider(color = BlogTheme.colors.border.divider)
    }
}

@Preview(widthDp = 1200, showBackground = true, backgroundColor = 0xFFE4E4E7)
@Preview(widthDp = 1200, showBackground = true, backgroundColor = 0xFF18181B, uiMode = 0x20)
@Composable
fun BlogTopBarPreview(
    @PreviewParameter(ScreenSizeParameterProvider::class) screenSize: ScreenSize,
) {
    Box(
        modifier = Modifier.width(width = (screenSize.size).dp).padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        BlogTheme {
            CompositionLocalProvider(LocalScreenSize provides screenSize) {
                BlogTopBar(
                    onThemeToggle = {},
                    onSelectLanguage = {}
                )
            }
        }
    }
}
