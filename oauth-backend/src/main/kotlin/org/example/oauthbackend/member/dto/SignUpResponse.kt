package org.example.oauthbackend.member.dto

import org.example.oauthbackend.auth.domain.TokenGroup
import org.example.oauthbackend.member.domain.Member

data class SignUpResponse(
    val member: Member,
    val tokenGroup: TokenGroup,
)
