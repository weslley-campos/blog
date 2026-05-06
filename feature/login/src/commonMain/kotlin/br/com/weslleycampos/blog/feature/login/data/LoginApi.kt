package br.com.weslleycampos.blog.feature.login.data

import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequest(
    val email: String,
    val password: String,
)
