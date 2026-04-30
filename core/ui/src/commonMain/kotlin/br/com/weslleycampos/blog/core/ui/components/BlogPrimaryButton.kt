package br.com.weslleycampos.blog.core.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

/**
 * Primary CTA button — gradient-filled, brand-shadowed.
 *
 * State spec:
 * | State    | Background          | Scale | Shadow                      |
 * |----------|---------------------|-------|-----------------------------|
 * | Default  | gradients.brand     | 1.00  | shadowButton @ alpha        |
 * | Hover    | gradients.brand     | 1.04  | shadowButton @ alpha + 4dp  |
 * | Pressed  | gradients.brand     | 0.96  | none                        |
 * | Disabled | surfaceContainerHigh| 1.00  | none                        |
 *
 * Hover is desktop/web-only via `Modifier.hoverable` → `collectIsHoveredAsState`.
 */
@Composable
fun BlogPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()
    val colors = BlogTheme.colors
    val shapes = BlogTheme.shapes

    val targetScale = when {
        !enabled -> 1f
        isPressed -> PRESSED_SCALE
        isHovered -> HOVER_SCALE
        else -> 1f
    }
    val animatedScale by animateFloatAsState(targetValue = targetScale)

    val shadowElevation = when {
        !enabled || isPressed -> 0.dp
        isHovered -> HOVER_SHADOW_ELEVATION
        else -> DEFAULT_SHADOW_ELEVATION
    }

    val backgroundBrush: Brush = if (enabled) {
        BlogTheme.gradients.brand
    } else {
        SolidColor(colors.surfaceContainerHigh)
    }

    Box(
        modifier = modifier
            .scale(animatedScale)
            .shadow(
                elevation = shadowElevation,
                shape = shapes.small,
                ambientColor = colors.shadowButtonColor,
                spotColor = colors.shadowButtonColor,
            )
            .clip(shapes.small)
            .background(brush = backgroundBrush)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            )
            .padding(horizontal = BlogTheme.spacing.xxl, vertical = BlogTheme.spacing.md),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = BlogTheme.typography.labelLarge,
            color = if (enabled) colors.interactivePrimaryText else colors.textMuted,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

private const val HOVER_SCALE = 1.04f
private const val PRESSED_SCALE = 0.96f
private val DEFAULT_SHADOW_ELEVATION = 6.dp
private val HOVER_SHADOW_ELEVATION = 10.dp

@Preview(widthDp = 600, heightDp = 320, showBackground = true)
@Composable
private fun BlogPrimaryButtonPreviewDark() {
    BlogTheme(isDarkMode = true) {
        ButtonPreviewSurface(background = BlogTheme.colors.background) {
            BlogPrimaryButton(text = "Default", onClick = {})
            BlogPrimaryButton(text = "Hire for Project", onClick = {})
            BlogPrimaryButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}

@Preview(widthDp = 600, heightDp = 320, showBackground = true)
@Composable
private fun BlogPrimaryButtonPreviewLight() {
    BlogTheme(isDarkMode = false) {
        ButtonPreviewSurface(background = BlogTheme.colors.background) {
            BlogPrimaryButton(text = "Default", onClick = {})
            BlogPrimaryButton(text = "Hire for Project", onClick = {})
            BlogPrimaryButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}

@Composable
internal fun ButtonPreviewSurface(
    background: Color,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}
