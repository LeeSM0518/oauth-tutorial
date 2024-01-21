package org.example.oauthbackend.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtConfigProperties(
    val secretKey: String,
    val accessTokenExpirationHour: Int,
    val refreshTokenExpirationHour: Int
)
