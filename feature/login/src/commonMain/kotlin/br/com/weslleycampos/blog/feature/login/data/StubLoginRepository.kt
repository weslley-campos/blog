package br.com.weslleycampos.blog.feature.login.data

import kotlinx.coroutines.delay
import org.koin.core.annotation.Single

/**
 * Demo stand-in for the real /login endpoint. The live backend
 * (poatek-mbp-weslley-campos.local:8080) only exists on the author's network
 * and the ticket explicitly accepts a stub. Specific email values trigger
 * each AC-mandated outcome so every state is reachable from the UI.
 */
@Single(binds = [LoginRepository::class])
class StubLoginRepository : LoginRepository {

    override suspend fun login(email: String, password: String): LoginResult {
        delay(SUBMISSION_DELAY_MS)
        return when {
            email.equals(OFFLINE_EMAIL, ignoreCase = true) -> LoginResult.NetworkError
            email.equals(CRASH_EMAIL, ignoreCase = true) -> LoginResult.UnexpectedError
            email.equals(VALID_EMAIL, ignoreCase = true) && password == VALID_PASSWORD ->
                LoginResult.Success
            else -> LoginResult.InvalidCredentials
        }
    }

    companion object {
        const val SUBMISSION_DELAY_MS = 700L
        const val VALID_EMAIL = "wesley@example.com"
        const val VALID_PASSWORD = "password123"
        const val OFFLINE_EMAIL = "offline@example.com"
        const val CRASH_EMAIL = "crash@example.com"
    }
}
