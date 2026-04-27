package br.com.weslleycampos.blog.feature.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.utils.LocalScreenSize
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize
import br.com.weslleycampos.blog.feature.about.components.AboutCtaCard
import br.com.weslleycampos.blog.feature.about.components.AboutEducation
import br.com.weslleycampos.blog.feature.about.components.AboutFooter
import br.com.weslleycampos.blog.feature.about.components.AboutHero
import br.com.weslleycampos.blog.feature.about.components.AboutTechArsenal
import br.com.weslleycampos.blog.feature.about.components.AboutTimeline
import br.com.weslleycampos.blog.feature.about.data.SocialLink

private const val URL_HIRE = "https://cal.com/weslley-campos"
private const val MAILTO_EMAIL = "mailto:weslley.campos@icloud.com"

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    val layout = layoutForScreenSize(LocalScreenSize.current)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BlogTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = layout.contentMaxWidth)
                .fillMaxWidth()
                .padding(
                    horizontal = layout.horizontalPadding,
                    vertical = layout.sectionGap,
                ),
            verticalArrangement = Arrangement.spacedBy(layout.sectionGap),
        ) {
            AboutHero(
                avatarSize = layout.avatarSize,
                bodyMaxWidth = layout.bodyMaxWidth,
            )
            SectionDivider()
            AboutTechArsenal()
            SectionDivider()
            AboutTimeline()
            SectionDivider()
            AboutEducation()
            SectionDivider()
            AboutCtaCard(
                onHireClick = { uriHandler.openUri(URL_HIRE) },
                onEmailClick = { uriHandler.openUri(MAILTO_EMAIL) },
            )
            AboutFooter(
                onLinkClick = { link ->
                    when (link) {
                        SocialLink.Github,
                        SocialLink.Linkedin,
                        SocialLink.Email,
                        -> uriHandler.openUri(link.url)
                    }
                },
            )
        }
    }
}

@Composable
private fun SectionDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(BlogTheme.gradients.navDivider),
    )
}

private data class AboutLayout(
    val horizontalPadding: Dp,
    val contentMaxWidth: Dp,
    val sectionGap: Dp,
    val avatarSize: Dp,
    val bodyMaxWidth: Dp,
)

private fun layoutForScreenSize(screenSize: ScreenSize): AboutLayout = when (screenSize) {
    ScreenSize.Compact -> AboutLayout(
        horizontalPadding = 24.dp,
        contentMaxWidth = Dp.Unspecified,
        sectionGap = 56.dp,
        avatarSize = 144.dp,
        bodyMaxWidth = Dp.Unspecified,
    )
    ScreenSize.Medium -> AboutLayout(
        horizontalPadding = 48.dp,
        contentMaxWidth = 720.dp,
        sectionGap = 72.dp,
        avatarSize = 168.dp,
        bodyMaxWidth = 640.dp,
    )
    ScreenSize.Expanded -> AboutLayout(
        horizontalPadding = 64.dp,
        contentMaxWidth = 1080.dp,
        sectionGap = 96.dp,
        avatarSize = 184.dp,
        bodyMaxWidth = 880.dp,
    )
}

@Preview(widthDp = 400, heightDp = 2200, showBackground = true)
@Composable
private fun AboutScreenCompactDarkPreview() {
    BlogTheme(isDarkMode = true) {
        CompositionLocalProvider(LocalScreenSize provides ScreenSize.Compact) {
            AboutScreen()
        }
    }
}

@Preview(widthDp = 400, heightDp = 2200, showBackground = true)
@Composable
private fun AboutScreenCompactLightPreview() {
    BlogTheme(isDarkMode = false) {
        CompositionLocalProvider(LocalScreenSize provides ScreenSize.Compact) {
            AboutScreen()
        }
    }
}

@Preview(widthDp = 768, heightDp = 2200, showBackground = true)
@Composable
private fun AboutScreenMediumDarkPreview() {
    BlogTheme(isDarkMode = true) {
        CompositionLocalProvider(LocalScreenSize provides ScreenSize.Medium) {
            AboutScreen()
        }
    }
}

@Preview(widthDp = 1440, heightDp = 2200, showBackground = true)
@Composable
private fun AboutScreenExpandedDarkPreview() {
    BlogTheme(isDarkMode = true) {
        CompositionLocalProvider(LocalScreenSize provides ScreenSize.Expanded) {
            AboutScreen()
        }
    }
}
