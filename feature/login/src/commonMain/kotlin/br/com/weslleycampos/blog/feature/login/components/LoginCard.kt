package br.com.weslleycampos.blog.feature.login.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.weslleycampos.blog.core.ui.components.BlogPrimaryButton
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.feature.login.LoginUiState

@Composable
internal fun LoginCard(
    state: LoginUiState,
    strings: LoginStrings,
    passwordFocusRequester: FocusRequester,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailBlur: () -> Unit,
    onPasswordBlur: () -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(BlogTheme.shapes.large)
            .background(color = BlogTheme.colors.surfaceElevated)
            .padding(BlogTheme.spacing.xxl),
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg),
    ) {
        LoginHeader(
            eyebrow = strings.eyebrow,
            title = strings.title,
            subtitle = strings.subtitle,
        )

        AnimatedVisibility(visible = state.formError != null) {
            FormErrorBanner(message = strings.invalidCredentials)
        }

        EmailField(
            value = state.email,
            onValueChange = onEmailChange,
            onBlur = onEmailBlur,
            label = strings.emailLabel,
            placeholder = strings.emailPlaceholder,
            errorMessage = state.emailError?.let { strings.emailInvalid },
            enabled = !state.isSubmitting,
        )

        PasswordField(
            value = state.password,
            onValueChange = onPasswordChange,
            onBlur = onPasswordBlur,
            onImeDone = onSubmit,
            onToggleVisibility = onTogglePasswordVisibility,
            isVisible = state.isPasswordVisible,
            label = strings.passwordLabel,
            placeholder = strings.passwordPlaceholder,
            visibilityToggleContentDescription = strings.passwordVisibilityA11y,
            errorMessage = state.passwordError?.let { strings.passwordTooShort },
            enabled = !state.isSubmitting,
            focusRequester = passwordFocusRequester,
        )

        SubmitButton(
            label = strings.submit,
            isSubmitting = state.isSubmitting,
            enabled = state.isSubmitEnabled,
            onClick = onSubmit,
        )
    }
}

@Composable
private fun LoginHeader(eyebrow: String, title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.xs)) {
        Text(
            text = eyebrow,
            style = BlogTheme.typography.labelSmall,
            color = BlogTheme.colors.textMuted,
        )
        Text(
            text = title,
            style = BlogTheme.typography.displaySmall,
            color = BlogTheme.colors.textPrimary,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = subtitle,
            style = BlogTheme.typography.bodyMedium,
            color = BlogTheme.colors.textSecondary,
        )
    }
}

@Composable
private fun SubmitButton(
    label: String,
    isSubmitting: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        BlogPrimaryButton(
            text = if (isSubmitting) "" else label,
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
        )
        if (isSubmitting) {
            CircularProgressIndicator(
                color = BlogTheme.colors.interactivePrimaryText,
                strokeWidth = SPINNER_STROKE_WIDTH,
                modifier = Modifier.size(SPINNER_SIZE).align(Alignment.Center),
            )
        }
    }
}

private val SPINNER_SIZE = 20.dp
private val SPINNER_STROKE_WIDTH = 2.dp

internal data class LoginStrings(
    val eyebrow: String,
    val title: String,
    val subtitle: String,
    val emailLabel: String,
    val emailPlaceholder: String,
    val emailInvalid: String,
    val passwordLabel: String,
    val passwordPlaceholder: String,
    val passwordTooShort: String,
    val passwordVisibilityA11y: String,
    val invalidCredentials: String,
    val submit: String,
)
