package org.example.oauthbackend.auth.config.oauth

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kakao")
data class KakaoOauthProperties(
    val clientId: String,
    val redirectUrl: String
)