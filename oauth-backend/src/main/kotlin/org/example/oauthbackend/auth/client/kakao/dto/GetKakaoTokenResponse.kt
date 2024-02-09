package org.example.oauthbackend.auth.client.kakao.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetKakaoTokenResponse(
    @JsonProperty("token_type")
    val tokenType: String?,
    @JsonProperty("access_token")
    val accessToken: String?,
    @JsonProperty("expires_in")
    val expiresIn: Int?,
    @JsonProperty("refresh_token")
    val refreshToken: String?,
    @JsonProperty("refresh_token_expires_in")
    val refreshTokenExpiresIn: Int?,
)