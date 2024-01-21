package org.example.oauthbackend.member.dto

import jakarta.validation.constraints.NotBlank

data class SignUpRequest(
    @NotBlank
    val email: String,
    @NotBlank
    val nickname: String
)
