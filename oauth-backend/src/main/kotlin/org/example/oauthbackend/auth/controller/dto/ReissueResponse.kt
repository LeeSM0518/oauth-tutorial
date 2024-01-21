package org.example.oauthbackend.auth.controller.dto

import org.example.oauthbackend.auth.domain.TokenGroup

data class ReissueResponse(
    val tokenGroup: TokenGroup
)
