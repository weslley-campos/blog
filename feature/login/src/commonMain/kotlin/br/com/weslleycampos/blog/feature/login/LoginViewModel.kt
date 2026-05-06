package br.com.weslleycampos.blog.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.weslleycampos.blog.feature.login.data.LoginRepository
import br.com.weslleycampos.blog.feature.login.data.LoginResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val repository: LoginRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.update {
            it.copy(
                email = email,
                emailError = null,
                formError = null,
            )
        }
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(
                password = password,
                passwordError = null,
                formError = null,
            )
        }
    }

    fun onEmailBlur() {
        val current = _state.value
        if (current.email.isBlank()) return
        if (!current.email.isValidEmail()) {
            _state.update { it.copy(emailError = EmailError.INVALID) }
        }
    }

    fun onPasswordBlur() {
        val current = _state.value
        if (current.password.isEmpty()) return
        if (current.password.length < MIN_PASSWORD_LENGTH) {
            _state.update { it.copy(passwordError = PasswordError.TOO_SHORT) }
        }
    }

    fun togglePasswordVisibility() {
        _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun submit() {
        val current = _state.value
        if (!current.isSubmitEnabled) return

        viewModelScope.launch {
            _state.update { it.copy(isSubmitting = true, formError = null, transientError = null) }
            val result = repository.login(current.email, current.password)
            applyResult(result)
        }
    }

    fun retry() = submit()

    fun consumeTransientError() {
        _state.update { it.copy(transientError = null) }
    }

    private fun applyResult(result: LoginResult) {
        _state.update { current ->
            when (result) {
                LoginResult.Success -> current.copy(
                    isSubmitting = false,
                    isAuthenticated = true,
                )
                LoginResult.InvalidCredentials -> current.copy(
                    isSubmitting = false,
                    password = "",
                    formError = FormError.INVALID_CREDENTIALS,
                )
                LoginResult.NetworkError -> current.copy(
                    isSubmitting = false,
                    transientError = TransientError.NETWORK,
                )
                LoginResult.UnexpectedError -> current.copy(
                    isSubmitting = false,
                    transientError = TransientError.UNEXPECTED,
                )
            }
        }
    }
}
