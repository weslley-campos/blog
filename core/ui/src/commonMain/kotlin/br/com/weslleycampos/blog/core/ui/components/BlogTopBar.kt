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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import br.com.weslleycampos.blog.core.ui.resources.topbar_about
import br.com.weslleycampos.blog.core.ui.resources.topbar_articles
import br.com.weslleycampos.blog.core.ui.resources.topbar_projects
import br.com.weslleycampos.blog.core.ui.resources.topbar_snippets
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.LocalDarkTheme
import br.com.weslleycampos.blog.core.ui.theme.painter
import br.com.weslleycampos.blog.core.ui.utils.LocalScreenSize
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize.Compact
import br.com.weslleycampos.blog.core.ui.utils.ScreenSizeParameterProvider
import org.jetbrains.compose.resources.stringResource

private const val ARTICLES = "Articles"
private const val SNIPPETS = "Snippets"
private const val PROJECTS = "Projects"
private const val ABOUT = "About"

@Composable
fun BlogTopBar(
    onThemeToggle: (Boolean) -> Unit,
    onSelectLanguage: (Locale) -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentScreenSize = LocalScreenSize.current
    var selected by remember { mutableStateOf(ABOUT) }
    var showLanguageSelector by remember { mutableStateOf(false) }

    when (currentScreenSize) {
        Compact -> CompactTopBar(
            onMenuClick = { },
            onLanguageClick = { showLanguageSelector = true },
            onThemeToggle = onThemeToggle,
            modifier = modifier,
        )

        else -> ExpandedTopBar(
            selected = selected,
            onItemClick = { selected = it },
            onThemeToggle = onThemeToggle,
            onLanguageClick = { showLanguageSelector = true },
            modifier = modifier,
        )
    }

    if (showLanguageSelector) {
        SelectLanguageDialog(
            onDismiss = { showLanguageSelector = false },
            onSelected = { onSelectLanguage(Locale(languageTag = it)) },
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
    val items = listOf(
        stringResource(CoreUiRes.string.topbar_articles) to ARTICLES,
        stringResource(CoreUiRes.string.topbar_snippets) to SNIPPETS,
        stringResource(CoreUiRes.string.topbar_projects) to PROJECTS,
        stringResource(CoreUiRes.string.topbar_about) to ABOUT,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BlogTheme.colors.surfaceNav),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = modifier
                .widthIn(max = maxSize)
                .fillMaxWidth()
                .height(BlogTheme.spacing.section)
                .padding(horizontal = BlogTheme.spacing.xxl),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GradientText(
                text = stringResource(CoreUiRes.string.author_name),
                brush = BlogTheme.gradients.brand,
                style = BlogTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            items.forEach { (label, key) ->
                NavBarItem(
                    label = label,
                    isSelected = selected == key,
                    onClick = { onItemClick(key) },
                )
                Spacer(modifier = Modifier.width(BlogTheme.spacing.sm))
            }
            Spacer(modifier = Modifier.width(BlogTheme.spacing.lg))
            LanguageIconButton(onClick = onLanguageClick)
            ThemeIconButton(onThemeToggle = onThemeToggle)
        }
        NavGradientDivider()
    }
}

@Composable
private fun NavBarItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = BlogTheme.spacing.md, vertical = BlogTheme.spacing.sm),
    ) {
        Text(
            text = label,
            style = BlogTheme.typography.bodyLarge,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = if (isSelected) BlogTheme.colors.brand else BlogTheme.colors.textSecondary,
        )
        Spacer(modifier = Modifier.height(BlogTheme.spacing.xs))
        Box(
            modifier = Modifier
                .height(NAV_UNDERLINE_HEIGHT)
                .fillMaxWidth()
                .background(
                    color = if (isSelected) BlogTheme.colors.brand else Color.Transparent,
                    shape = BlogTheme.shapes.full,
                ),
        )
    }
}

@Composable
private fun NavGradientDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(NAV_DIVIDER_HEIGHT)
            .background(BlogTheme.gradients.navDivider),
    )
}

@Composable
private fun LanguageIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = BlogTheme.icons.Translate.painter,
            contentDescription = stringResource(CoreUiRes.string.a11y_language_button),
            tint = BlogTheme.colors.textPrimary,
            modifier = Modifier.size(BlogTheme.sizes.icon.small),
        )
    }
}

@Composable
private fun ThemeIconButton(onThemeToggle: (Boolean) -> Unit) {
    val isDarkThemeEnabled = LocalDarkTheme.current
    IconButton(onClick = { onThemeToggle.invoke(!isDarkThemeEnabled) }) {
        Icon(
            painter = BlogTheme.icons.run { if (isDarkThemeEnabled) Moon else Sun }.painter,
            contentDescription = stringResource(CoreUiRes.string.a11y_theme_mode_button),
            tint = BlogTheme.colors.textPrimary,
            modifier = Modifier.size(BlogTheme.sizes.icon.small),
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
        modifier = Modifier
            .fillMaxWidth()
            .background(BlogTheme.colors.surfaceNav),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = modifier
                .widthIn(max = maxSize)
                .fillMaxWidth()
                .height(BlogTheme.spacing.massive)
                .padding(horizontal = BlogTheme.spacing.lg),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GradientText(
                text = stringResource(CoreUiRes.string.author_name),
                brush = BlogTheme.gradients.brand,
                style = BlogTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.weight(1f))
            ThemeIconButton(onThemeToggle = onThemeToggle)
            LanguageIconButton(onClick = onLanguageClick)
            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = BlogTheme.icons.Menu.painter,
                    contentDescription = stringResource(CoreUiRes.string.a11y_menu_button),
                    tint = BlogTheme.colors.textPrimary,
                    modifier = Modifier.size(BlogTheme.sizes.icon.small),
                )
            }
        }
        NavGradientDivider()
    }
}

private val NAV_UNDERLINE_HEIGHT = 2.dp
private val NAV_DIVIDER_HEIGHT = 1.dp

@Preview(widthDp = 1200, showBackground = true, backgroundColor = 0xFFE4E4E7)
@Preview(widthDp = 1200, showBackground = true, backgroundColor = 0xFF18181B, uiMode = 0x20)
@Composable
fun BlogTopBarPreview(
    @PreviewParameter(ScreenSizeParameterProvider::class) screenSize: ScreenSize,
) {
    Box(
        modifier = Modifier.width(width = screenSize.size.dp).padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        BlogTheme {
            CompositionLocalProvider(LocalScreenSize provides screenSize) {
                BlogTopBar(
                    onThemeToggle = {},
                    onSelectLanguage = {},
                )
            }
        }
    }
}
