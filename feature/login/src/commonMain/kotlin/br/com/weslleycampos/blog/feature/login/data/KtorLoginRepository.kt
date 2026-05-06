package br.com.weslleycampos.blog.feature.login.data

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.CancellationException
import org.koin.core.annotation.Single

@Single(binds = [LoginRepository::class])
class KtorLoginRepository(
    private val httpClient: HttpClient,
) : LoginRepository {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val response = httpClient.post(LOGIN_URL) {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(email = email, password = password))
            }
            when (response.status) {
                HttpStatusCode.OK,
                HttpStatusCode.Created,
                HttpStatusCode.NoContent,
                -> LoginResult.Success
                HttpStatusCode.Unauthorized,
                HttpStatusCode.Forbidden,
                -> LoginResult.InvalidCredentials
                else -> LoginResult.UnexpectedError
            }
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (error: Throwable) {
            error.toLoginResult()
        }
    }

    private fun Throwable.toLoginResult(): LoginResult {
        val name = this::class.simpleName.orEmpty()
        return if (NETWORK_ERROR_NAMES.any { it in name }) {
            LoginResult.NetworkError
        } else {
            LoginResult.UnexpectedError
        }
    }

    private companion object {
        const val LOGIN_URL = "http://poatek-mbp-weslley-campos.local:8080/login"
        val NETWORK_ERROR_NAMES = listOf(
            "Timeout",
            "Connect",
            "Socket",
            "UnknownHost",
            "Network",
            "IO",
        )
    }
}
