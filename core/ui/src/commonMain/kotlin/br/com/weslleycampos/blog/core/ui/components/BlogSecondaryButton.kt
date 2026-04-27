package br.com.weslleycampos.blog.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

/**
 * Secondary CTA button — solid `surfaceContainerHigh`, brand-tinted on hover.
 *
 * State spec:
 * | State    | Background                              | Border           | Text                       |
 * |----------|------------------------------------------|------------------|----------------------------|
 * | Default  | surfaceContainerHigh                     | none             | interactiveSecondaryText   |
 * | Hover    | surfaceContainerHigh + brand @ 0.06      | brand @ 0.30     | interactiveSecondaryText   |
 * | Pressed  | surfaceContainerHigh + brand @ 0.12      | brand @ 0.50     | interactiveSecondaryText   |
 * | Disabled | surfaceContainerHigh @ 0.50              | none             | textMuted                  |
 */
@Composable
fun BlogSecondaryButton(
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

    val baseFill = colors.surfaceContainerHigh
    val backgroundColor = when {
        !enabled -> baseFill.copy(alpha = DISABLED_FILL_ALPHA)
        isPressed -> colors.brand.copy(alpha = PRESSED_TINT_ALPHA).compositeOver(baseFill)
        isHovered -> colors.brand.copy(alpha = HOVER_TINT_ALPHA).compositeOver(baseFill)
        else -> baseFill
    }

    val borderColor: Color = when {
        !enabled -> Color.Transparent
        isPressed -> colors.brand.copy(alpha = PRESSED_BORDER_ALPHA)
        isHovered -> colors.brand.copy(alpha = HOVER_BORDER_ALPHA)
        else -> Color.Transparent
    }

    Box(
        modifier = modifier
            .clip(shapes.small)
            .background(backgroundColor)
            .border(width = 1.dp, color = borderColor, shape = shapes.small)
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
            color = if (enabled) colors.interactiveSecondaryText else colors.textMuted,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

private const val HOVER_TINT_ALPHA = 0.06f
private const val PRESSED_TINT_ALPHA = 0.12f
private const val HOVER_BORDER_ALPHA = 0.30f
private const val PRESSED_BORDER_ALPHA = 0.50f
private const val DISABLED_FILL_ALPHA = 0.50f

@Preview(widthDp = 600, heightDp = 320, showBackground = true)
@Composable
private fun BlogSecondaryButtonPreviewDark() {
    BlogTheme(isDarkMode = true) {
        ButtonPreviewSurface(background = BlogTheme.colors.background) {
            BlogSecondaryButton(text = "Default", onClick = {})
            BlogSecondaryButton(text = "Send Email", onClick = {})
            BlogSecondaryButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}

@Preview(widthDp = 600, heightDp = 320, showBackground = true)
@Composable
private fun BlogSecondaryButtonPreviewLight() {
    BlogTheme(isDarkMode = false) {
        ButtonPreviewSurface(background = BlogTheme.colors.background) {
            BlogSecondaryButton(text = "Default", onClick = {})
            BlogSecondaryButton(text = "Send Email", onClick = {})
            BlogSecondaryButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}
