package br.com.weslleycampos.blog.feature.login

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class LoginModule {

    @Single
    fun provideHttpClient(): HttpClient = HttpClient {
        expectSuccess = false
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                },
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = REQUEST_TIMEOUT_MS
            connectTimeoutMillis = CONNECT_TIMEOUT_MS
        }
    }

    private companion object {
        const val REQUEST_TIMEOUT_MS = 10_000L
        const val CONNECT_TIMEOUT_MS = 5_000L
    }
}
