package org.example.oauthbackend.auth.controller.dto

import jakarta.validation.constraints.NotBlank
import org.example.oauthbackend.auth.domain.RefreshToken

data class ReissueRequest(
    @NotBlank
    val refreshToken: RefreshToken
)
