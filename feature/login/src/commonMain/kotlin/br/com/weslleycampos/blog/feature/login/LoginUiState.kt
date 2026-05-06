package br.com.weslleycampos.blog.feature.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null,
    val formError: FormError? = null,
    val transientError: TransientError? = null,
    val isSubmitting: Boolean = false,
    val isAuthenticated: Boolean = false,
) {
    val isFormSyntacticallyValid: Boolean
        get() = email.isValidEmail() && password.length >= MIN_PASSWORD_LENGTH

    val isSubmitEnabled: Boolean
        get() = !isSubmitting && isFormSyntacticallyValid
}

enum class EmailError { INVALID }
enum class PasswordError { TOO_SHORT }
enum class FormError { INVALID_CREDENTIALS }
enum class TransientError { NETWORK, UNEXPECTED }

const val MIN_PASSWORD_LENGTH = 8

private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$")

fun String.isValidEmail(): Boolean = EMAIL_REGEX.matches(this)
