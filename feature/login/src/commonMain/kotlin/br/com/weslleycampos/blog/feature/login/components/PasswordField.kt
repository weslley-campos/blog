package br.com.weslleycampos.blog.feature.login.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

@Composable
internal fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    onBlur: () -> Unit,
    onImeDone: () -> Unit,
    onToggleVisibility: () -> Unit,
    isVisible: Boolean,
    label: String,
    placeholder: String,
    visibilityToggleContentDescription: String,
    errorMessage: String?,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester? = null,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f)) {
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .let { if (focusRequester != null) it.focusRequester(focusRequester) else it }
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused && wasFocused) onBlur()
                            },
                        enabled = enabled,
                        singleLine = true,
                        interactionSource = interactionSource,
                        textStyle = LocalTextStyle.current.merge(
                            BlogTheme.typography.bodyMedium.copy(
                                color = BlogTheme.colors.textPrimary,
                            ),
                        ),
                        cursorBrush = SolidColor(BlogTheme.colors.brand),
                        visualTransformation = if (isVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = false,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(onDone = { onImeDone() }),
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
                IconButton(
                    onClick = onToggleVisibility,
                    enabled = enabled,
                    modifier = Modifier.size(EYE_BUTTON_SIZE),
                ) {
                    Icon(
                        imageVector = if (isVisible) {
                            Icons.Outlined.VisibilityOff
                        } else {
                            Icons.Outlined.Visibility
                        },
                        contentDescription = visibilityToggleContentDescription,
                        tint = BlogTheme.colors.textSecondary,
                        modifier = Modifier.size(BlogTheme.sizes.icon.small),
                    )
                }
            }
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

private val EYE_BUTTON_SIZE = 32.dp
