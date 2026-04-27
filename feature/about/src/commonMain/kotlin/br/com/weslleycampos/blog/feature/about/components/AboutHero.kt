package br.com.weslleycampos.blog.feature.about.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.weslleycampos.blog.core.ui.components.SectionEyebrow
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.about_avatar_initials
import br.com.weslleycampos.blog.core.ui.resources.about_bio_paragraph
import br.com.weslleycampos.blog.core.ui.resources.about_eyebrow_about_me
import br.com.weslleycampos.blog.core.ui.resources.about_hero_headline
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.InterFontFamily
import br.com.weslleycampos.blog.core.ui.theme.Neutral0
import br.com.weslleycampos.blog.core.ui.theme.Deep7
import org.jetbrains.compose.resources.stringResource

/**
 * Top-of-page hero — gradient-ring avatar with WC initials, code-comment
 * eyebrow, "Hi, I'm Weslley." headline in brand color, short identity
 * subtitle. Everything is centered along the cross-axis so the section
 * reads as a focal block on Compact and Medium breakpoints.
 *
 * `bodyMaxWidth` caps the subtitle column on wider breakpoints; default
 * `Dp.Unspecified` lets compact use the full container width.
 */
@Composable
internal fun AboutHero(
    modifier: Modifier = Modifier,
    avatarSize: Dp = HERO_AVATAR_SIZE,
    bodyMaxWidth: Dp = Dp.Unspecified,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xl),
    ) {
        AvatarRing(
            initials = stringResource(CoreUiRes.string.about_avatar_initials),
            size = avatarSize,
        )
        SectionEyebrow(text = stringResource(CoreUiRes.string.about_eyebrow_about_me))
        Text(
            text = stringResource(CoreUiRes.string.about_hero_headline),
            style = BlogTheme.typography.displaySmall,
            color = BlogTheme.colors.brand,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(CoreUiRes.string.about_bio_paragraph),
            style = BlogTheme.typography.bodyLarge,
            color = BlogTheme.colors.textSecondary,
            textAlign = TextAlign.Justify,
            modifier = Modifier.widthIn(max = bodyMaxWidth),
        )
    }
}

/**
 * Avatar — circular dark fill (always Deep7 in both themes) wrapped in a
 * brand-gradient ring, with monogram initials in white. Initial font scales
 * with the circle so a single component handles every breakpoint.
 */
@Composable
private fun AvatarRing(
    initials: String,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Deep7)
            .border(
                width = AVATAR_RING_WIDTH,
                brush = BlogTheme.gradients.brand,
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = initials,
            style = TextStyle(
                fontFamily = InterFontFamily,
                fontSize = (size.value * AVATAR_INITIALS_RATIO).sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.02).sp,
            ),
            color = Neutral0,
        )
    }
}

private val HERO_AVATAR_SIZE = 144.dp
private val AVATAR_RING_WIDTH = 3.dp
private const val AVATAR_INITIALS_RATIO = 0.34f

@Preview(widthDp = 400, heightDp = 600, showBackground = true)
@Composable
private fun AboutHeroPreviewDark() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutHero()
        }
    }
}

@Preview(widthDp = 400, heightDp = 600, showBackground = true)
@Composable
private fun AboutHeroPreviewLight() {
    BlogTheme(isDarkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.xxl),
        ) {
            AboutHero()
        }
    }
}

@Preview(widthDp = 768, heightDp = 600, showBackground = true)
@Composable
private fun AboutHeroPreviewTablet() {
    BlogTheme(isDarkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlogTheme.colors.background)
                .padding(BlogTheme.spacing.huge),
        ) {
            AboutHero(avatarSize = 168.dp, bodyMaxWidth = 480.dp)
        }
    }
}
