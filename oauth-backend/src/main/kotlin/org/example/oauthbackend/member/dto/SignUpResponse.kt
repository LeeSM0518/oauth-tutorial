package org.example.oauthbackend.member.dto

import java.util.UUID

data class SignUpResponse(
    val memberId: UUID,
    val nickname: String,
    val email: String
)
