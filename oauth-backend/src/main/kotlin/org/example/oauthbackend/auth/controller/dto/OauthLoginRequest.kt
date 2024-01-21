package org.example.oauthbackend.auth.controller.dto

import jakarta.validation.constraints.NotBlank
import org.example.oauthbackend.auth.domain.OauthType

data class OauthLoginRequest(
    @NotBlank
    val authorizationCode: String,
    @NotBlank
    val oauthType: OauthType
)
