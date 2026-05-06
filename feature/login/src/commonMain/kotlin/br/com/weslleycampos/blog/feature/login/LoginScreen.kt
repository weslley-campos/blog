package br.com.weslleycampos.blog.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.a11y_login_password_visibility
import br.com.weslleycampos.blog.core.ui.resources.login_authenticated_subtitle
import br.com.weslleycampos.blog.core.ui.resources.login_authenticated_title
import br.com.weslleycampos.blog.core.ui.resources.login_email_invalid
import br.com.weslleycampos.blog.core.ui.resources.login_email_label
import br.com.weslleycampos.blog.core.ui.resources.login_email_placeholder
import br.com.weslleycampos.blog.core.ui.resources.login_eyebrow
import br.com.weslleycampos.blog.core.ui.resources.login_invalid_credentials
import br.com.weslleycampos.blog.core.ui.resources.login_network_error
import br.com.weslleycampos.blog.core.ui.resources.login_password_label
import br.com.weslleycampos.blog.core.ui.resources.login_password_placeholder
import br.com.weslleycampos.blog.core.ui.resources.login_password_too_short
import br.com.weslleycampos.blog.core.ui.resources.login_retry
import br.com.weslleycampos.blog.core.ui.resources.login_submit
import br.com.weslleycampos.blog.core.ui.resources.login_subtitle
import br.com.weslleycampos.blog.core.ui.resources.login_title
import br.com.weslleycampos.blog.core.ui.resources.login_unexpected_error
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.feature.login.components.LoginCard
import br.com.weslleycampos.blog.feature.login.components.LoginStrings
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onEmailBlur = viewModel::onEmailBlur,
        onPasswordBlur = viewModel::onPasswordBlur,
        onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
        onSubmit = viewModel::submit,
        onRetry = viewModel::retry,
        onTransientErrorConsumed = viewModel::consumeTransientError,
        modifier = modifier,
    )
}

@Composable
internal fun LoginScreen(
    state: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailBlur: () -> Unit,
    onPasswordBlur: () -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onSubmit: () -> Unit,
    onRetry: () -> Unit,
    onTransientErrorConsumed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val passwordFocusRequester = remember { FocusRequester() }
    val strings = rememberLoginStrings()

    val networkMessage = stringResource(CoreUiRes.string.login_network_error)
    val unexpectedMessage = stringResource(CoreUiRes.string.login_unexpected_error)
    val retryLabel = stringResource(CoreUiRes.string.login_retry)

    LaunchedEffect(state.formError) {
        if (state.formError == FormError.INVALID_CREDENTIALS) {
            passwordFocusRequester.requestFocus()
        }
    }

    LaunchedEffect(state.transientError) {
        val transient = state.transientError ?: return@LaunchedEffect
        val message = when (transient) {
            TransientError.NETWORK -> networkMessage
            TransientError.UNEXPECTED -> unexpectedMessage
        }
        val result = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = retryLabel,
            withDismissAction = true,
        )
        onTransientErrorConsumed()
        if (result == SnackbarResult.ActionPerformed) onRetry()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BlogTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = LOGIN_MAX_WIDTH)
                .padding(horizontal = BlogTheme.spacing.xxl, vertical = BlogTheme.spacing.huge),
            verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.lg),
        ) {
            if (state.isAuthenticated) {
                AuthenticatedView()
            } else {
                LoginCard(
                    state = state,
                    strings = strings,
                    passwordFocusRequester = passwordFocusRequester,
                    onEmailChange = onEmailChange,
                    onPasswordChange = onPasswordChange,
                    onEmailBlur = onEmailBlur,
                    onPasswordBlur = onPasswordBlur,
                    onTogglePasswordVisibility = onTogglePasswordVisibility,
                    onSubmit = onSubmit,
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(BlogTheme.spacing.lg),
        ) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = BlogTheme.colors.surfaceElevated,
                contentColor = BlogTheme.colors.textPrimary,
                actionColor = BlogTheme.colors.brand,
            )
        }
    }
}

@Composable
private fun AuthenticatedView() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BlogTheme.spacing.sm),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(CoreUiRes.string.login_authenticated_title),
            style = BlogTheme.typography.displaySmall,
            color = BlogTheme.colors.textPrimary,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(CoreUiRes.string.login_authenticated_subtitle),
            style = BlogTheme.typography.bodyMedium,
            color = BlogTheme.colors.textSecondary,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun rememberLoginStrings(): LoginStrings = LoginStrings(
    eyebrow = stringResource(CoreUiRes.string.login_eyebrow),
    title = stringResource(CoreUiRes.string.login_title),
    subtitle = stringResource(CoreUiRes.string.login_subtitle),
    emailLabel = stringResource(CoreUiRes.string.login_email_label),
    emailPlaceholder = stringResource(CoreUiRes.string.login_email_placeholder),
    emailInvalid = stringResource(CoreUiRes.string.login_email_invalid),
    passwordLabel = stringResource(CoreUiRes.string.login_password_label),
    passwordPlaceholder = stringResource(CoreUiRes.string.login_password_placeholder),
    passwordTooShort = stringResource(CoreUiRes.string.login_password_too_short),
    passwordVisibilityA11y = stringResource(CoreUiRes.string.a11y_login_password_visibility),
    invalidCredentials = stringResource(CoreUiRes.string.login_invalid_credentials),
    submit = stringResource(CoreUiRes.string.login_submit),
)

private val LOGIN_MAX_WIDTH = 480.dp

@Preview(widthDp = 400, heightDp = 720, showBackground = true)
@Composable
private fun LoginScreenIdleDarkPreview() {
    BlogTheme(isDarkMode = true) {
        LoginScreen(
            state = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onEmailBlur = {},
            onPasswordBlur = {},
            onTogglePasswordVisibility = {},
            onSubmit = {},
            onRetry = {},
            onTransientErrorConsumed = {},
        )
    }
}

@Preview(widthDp = 400, heightDp = 720, showBackground = true)
@Composable
private fun LoginScreenSubmittingDarkPreview() {
    BlogTheme(isDarkMode = true) {
        LoginScreen(
            state = LoginUiState(
                email = "wesley@example.com",
                password = "password123",
                isSubmitting = true,
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onEmailBlur = {},
            onPasswordBlur = {},
            onTogglePasswordVisibility = {},
            onSubmit = {},
            onRetry = {},
            onTransientErrorConsumed = {},
        )
    }
}

@Preview(widthDp = 400, heightDp = 720, showBackground = true)
@Composable
private fun LoginScreenInvalidCredentialsDarkPreview() {
    BlogTheme(isDarkMode = true) {
        LoginScreen(
            state = LoginUiState(
                email = "wesley@example.com",
                password = "",
                formError = FormError.INVALID_CREDENTIALS,
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onEmailBlur = {},
            onPasswordBlur = {},
            onTogglePasswordVisibility = {},
            onSubmit = {},
            onRetry = {},
            onTransientErrorConsumed = {},
        )
    }
}

@Preview(widthDp = 400, heightDp = 720, showBackground = true)
@Composable
private fun LoginScreenAuthenticatedDarkPreview() {
    BlogTheme(isDarkMode = true) {
        LoginScreen(
            state = LoginUiState(isAuthenticated = true),
            onEmailChange = {},
            onPasswordChange = {},
            onEmailBlur = {},
            onPasswordBlur = {},
            onTogglePasswordVisibility = {},
            onSubmit = {},
            onRetry = {},
            onTransientErrorConsumed = {},
        )
    }
}
