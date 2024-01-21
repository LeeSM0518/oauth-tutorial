package org.example.oauthbackend.auth.controller.dto

import org.example.oauthbackend.auth.domain.TokenGroup
import org.example.oauthbackend.member.domain.Member

data class OauthLoginResponse(
    val member: Member,
    val tokenGroup: TokenGroup
)
