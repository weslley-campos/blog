package br.com.weslleycampos.blog.feature.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

@Composable
internal fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    onBlur: () -> Unit,
    label: String,
    placeholder: String,
    errorMessage: String?,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var wasFocused by remember { mutableStateOf(false) }

    LaunchedEffect(isFocused) {
        if (isFocused) wasFocused = true
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.sm),
    ) {
        Text(
            text = label,
            style = BlogTheme.typography.labelSmall,
            color = BlogTheme.colors.textSecondary,
        )

        FieldSurface(
            isFocused = isFocused,
            hasError = errorMessage != null,
            enabled = enabled,
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused && wasFocused) onBlur()
                    },
                enabled = enabled,
                singleLine = true,
                interactionSource = interactionSource,
                textStyle = LocalTextStyle.current.merge(
                    BlogTheme.typography.bodyMedium.copy(color = BlogTheme.colors.textPrimary),
                ),
                cursorBrush = SolidColor(BlogTheme.colors.brand),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = imeAction,
                ),
                decorationBox = { inner ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = BlogTheme.typography.bodyMedium,
                            color = BlogTheme.colors.textMuted,
                        )
                    }
                    inner()
                },
            )
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                style = BlogTheme.typography.bodySmall,
                color = BlogTheme.colors.statusDangerAccent,
            )
        }
    }
}

@Composable
internal fun FieldSurface(
    isFocused: Boolean,
    hasError: Boolean,
    enabled: Boolean,
    content: @Composable () -> Unit,
) {
    val colors = BlogTheme.colors
    val borderColor = when {
        hasError -> colors.statusDangerAccent
        isFocused -> colors.brand
        else -> colors.surfaceContainerHigh
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(BlogTheme.shapes.small)
            .background(color = colors.surfaceContainerHigh)
            .border(
                width = if (isFocused || hasError) FOCUSED_BORDER_WIDTH else IDLE_BORDER_WIDTH,
                color = borderColor,
                shape = BlogTheme.shapes.small,
            )
            .padding(horizontal = BlogTheme.spacing.lg, vertical = BlogTheme.spacing.md),
    ) {
        CompositionLocalProvider(
            LocalContentColor provides if (enabled) colors.textPrimary else colors.textMuted,
            content = content,
        )
    }
}

private val FOCUSED_BORDER_WIDTH = 1.5.dp
private val IDLE_BORDER_WIDTH = 1.dp
