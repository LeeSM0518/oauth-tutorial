package org.example.oauthbackend.member.dto

import jakarta.validation.constraints.NotBlank
import org.example.oauthbackend.auth.domain.OauthType
import org.example.oauthbackend.member.entity.OauthId

data class SignUpRequest(
    @NotBlank
    val oauthId: OauthId,
    @NotBlank
    val nickname: String,
    @NotBlank
    val oauthType: OauthType
)
