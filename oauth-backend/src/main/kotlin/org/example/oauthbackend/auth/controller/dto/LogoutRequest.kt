package org.example.oauthbackend.auth.controller.dto

import jakarta.validation.constraints.NotBlank
import org.example.oauthbackend.auth.domain.RefreshToken

data class LogoutRequest(
    @NotBlank
    val refreshToken: RefreshToken
)
