package org.example.oauthbackend.auth.client.naver.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetNaverTokenResponse(
    @JsonProperty("access_token")
    private val accessToken: String?,
    @JsonProperty("refresh_token")
    val refreshToken: String?,
    @JsonProperty("token_token")
    val tokenType: String?,
    @JsonProperty("expires_in")
    val expiresIn: String?,
    @JsonProperty("error")
    val error: String?,
    @JsonProperty("error_description")
    val errorDescription: String?,
) {
    val token: String? = accessToken?.let { "Bearer $accessToken" }
}
