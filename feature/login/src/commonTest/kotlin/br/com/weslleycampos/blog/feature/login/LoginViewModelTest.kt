package br.com.weslleycampos.blog.feature.login

import br.com.weslleycampos.blog.feature.login.data.LoginRepository
import br.com.weslleycampos.blog.feature.login.data.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun ac1_validCredentials_emitsAuthenticatedSuccess() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository(LoginResult.Success))

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()

        val state = viewModel.state.value
        assertTrue(state.isAuthenticated)
        assertFalse(state.isSubmitting)
        assertNull(state.formError)
        assertNull(state.transientError)
    }

    @Test
    fun ac2_emailBlur_invalidPattern_setsInlineError_keepsButtonDisabled() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository())

        viewModel.onEmailChange("not-an-email")
        viewModel.onPasswordChange("password123")
        viewModel.onEmailBlur()

        val state = viewModel.state.value
        assertEquals(EmailError.INVALID, state.emailError)
        assertFalse(state.isSubmitEnabled)
    }

    @Test
    fun ac3_passwordBlur_belowMin_setsInlineError_keepsButtonDisabled() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository())

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("short")
        viewModel.onPasswordBlur()

        val state = viewModel.state.value
        assertEquals(PasswordError.TOO_SHORT, state.passwordError)
        assertFalse(state.isSubmitEnabled)
    }

    @Test
    fun ac4_bothFieldsSyntacticallyValid_enablesSignInButton() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository())

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")

        assertTrue(viewModel.state.value.isSubmitEnabled)
    }

    @Test
    fun ac5_togglePasswordVisibility_flipsState() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository())

        assertFalse(viewModel.state.value.isPasswordVisible)
        viewModel.togglePasswordVisibility()
        assertTrue(viewModel.state.value.isPasswordVisible)
        viewModel.togglePasswordVisibility()
        assertFalse(viewModel.state.value.isPasswordVisible)
    }

    @Test
    fun ac6_imeDone_triggersSubmissionWhenValid() = runTest {
        val repository = FakeLoginRepository(LoginResult.Success)
        val viewModel = LoginViewModel(repository)

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()

        assertEquals("user@example.com", repository.lastEmail)
        assertEquals("password123", repository.lastPassword)
        assertTrue(viewModel.state.value.isAuthenticated)
    }

    @Test
    fun ac6_imeDone_doesNotSubmitWhenFormInvalid() = runTest {
        val repository = FakeLoginRepository(LoginResult.Success)
        val viewModel = LoginViewModel(repository)

        viewModel.onEmailChange("invalid")
        viewModel.onPasswordChange("short")
        viewModel.submit()

        assertNull(repository.lastEmail)
        assertFalse(viewModel.state.value.isAuthenticated)
    }

    @Test
    fun ac7_userInputAndVisibilityPersist_acrossUnrelatedOperations() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository())

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.togglePasswordVisibility()
        viewModel.onEmailBlur()
        viewModel.onPasswordBlur()

        val state = viewModel.state.value
        assertEquals("user@example.com", state.email)
        assertEquals("password123", state.password)
        assertTrue(state.isPasswordVisible)
    }

    @Test
    fun l1_inFlightSubmission_disablesButton_andSettlesAfterResult() = runTest {
        val gated = GatedFakeLoginRepository(LoginResult.Success)
        val viewModel = LoginViewModel(gated)

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()

        val midFlight = viewModel.state.value
        assertTrue(midFlight.isSubmitting)
        assertFalse(midFlight.isSubmitEnabled)

        gated.complete()

        val settled = viewModel.state.value
        assertFalse(settled.isSubmitting)
        assertTrue(settled.isAuthenticated)
    }

    @Test
    fun l1_repeatSubmit_whileInFlight_isIgnored() = runTest {
        val gated = GatedFakeLoginRepository(LoginResult.Success)
        val viewModel = LoginViewModel(gated)

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()
        viewModel.submit()

        gated.complete()
        assertEquals(1, gated.callCount)
    }

    @Test
    fun e3_invalidCredentials_setsFormError_clearsPassword() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository(LoginResult.InvalidCredentials))

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()

        val state = viewModel.state.value
        assertEquals(FormError.INVALID_CREDENTIALS, state.formError)
        assertEquals("", state.password)
        assertEquals("user@example.com", state.email)
        assertFalse(state.isSubmitting)
    }

    @Test
    fun e4_networkError_emitsTransientNetworkError() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository(LoginResult.NetworkError))

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()

        val state = viewModel.state.value
        assertEquals(TransientError.NETWORK, state.transientError)
        assertNull(state.formError)
        assertFalse(state.isSubmitting)
    }

    @Test
    fun e5_unexpectedError_emitsTransientUnexpectedError() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository(LoginResult.UnexpectedError))

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()

        val state = viewModel.state.value
        assertEquals(TransientError.UNEXPECTED, state.transientError)
        assertNull(state.formError)
        assertFalse(state.isSubmitting)
    }

    @Test
    fun inlineError_clearsOnEdit() = runTest {
        val viewModel = LoginViewModel(FakeLoginRepository())

        viewModel.onEmailChange("not-an-email")
        viewModel.onEmailBlur()
        assertEquals(EmailError.INVALID, viewModel.state.value.emailError)

        viewModel.onEmailChange("user@example.com")
        assertNull(viewModel.state.value.emailError)
    }

    @Test
    fun formError_clearsOnEditOrResubmit() = runTest {
        val repository = FakeLoginRepository(LoginResult.InvalidCredentials)
        val viewModel = LoginViewModel(repository)

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()
        assertEquals(FormError.INVALID_CREDENTIALS, viewModel.state.value.formError)

        viewModel.onEmailChange("user2@example.com")
        assertNull(viewModel.state.value.formError)

        viewModel.onPasswordChange("password123")
        repository.nextResult = LoginResult.Success
        viewModel.submit()
        assertNull(viewModel.state.value.formError)
        assertTrue(viewModel.state.value.isAuthenticated)
    }

    @Test
    fun retry_consumesTransientError_andResubmits() = runTest {
        val repository = FakeLoginRepository(LoginResult.NetworkError)
        val viewModel = LoginViewModel(repository)

        viewModel.onEmailChange("user@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.submit()
        assertEquals(TransientError.NETWORK, viewModel.state.value.transientError)

        repository.nextResult = LoginResult.Success
        viewModel.consumeTransientError()
        viewModel.retry()

        val state = viewModel.state.value
        assertNull(state.transientError)
        assertTrue(state.isAuthenticated)
    }
}

private class FakeLoginRepository(
    var nextResult: LoginResult = LoginResult.Success,
) : LoginRepository {
    var lastEmail: String? = null
    var lastPassword: String? = null

    override suspend fun login(email: String, password: String): LoginResult {
        lastEmail = email
        lastPassword = password
        return nextResult
    }
}

private class GatedFakeLoginRepository(
    private val result: LoginResult,
) : LoginRepository {
    private val gate = kotlinx.coroutines.CompletableDeferred<Unit>()
    var callCount = 0
        private set

    override suspend fun login(email: String, password: String): LoginResult {
        callCount += 1
        gate.await()
        return result
    }

    fun complete() {
        gate.complete(Unit)
    }
}
