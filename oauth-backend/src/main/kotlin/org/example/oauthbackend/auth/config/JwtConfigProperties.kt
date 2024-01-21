package org.example.oauthbackend.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtConfigProperties(
    val accessTokenSecretKey: String,
    val refreshTokenSecretKey: String,
    val accessTokenExpirationHour: Int,
    val refreshTokenExpirationHour: Int
)
