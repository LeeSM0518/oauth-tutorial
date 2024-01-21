package org.example.oauthbackend.auth.domain

typealias AccessToken = String
typealias RefreshToken = String

data class TokenGroup(
    val accessToken: AccessToken,
    val refreshToken: RefreshToken
)
