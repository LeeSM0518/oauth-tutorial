package org.example.oauthbackend.auth.domain

import java.util.UUID

data class TokenSubject(
    val memberId: UUID
)