package br.com.weslleycampos.blog.feature.login.data

interface LoginRepository {
    suspend fun login(email: String, password: String): LoginResult
}

sealed interface LoginResult {
    data object Success : LoginResult
    data object InvalidCredentials : LoginResult
    data object NetworkError : LoginResult
    data object UnexpectedError : LoginResult
}
