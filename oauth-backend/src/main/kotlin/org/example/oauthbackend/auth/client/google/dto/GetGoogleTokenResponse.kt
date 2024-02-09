package org.example.oauthbackend.auth.client.google.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetGoogleTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String?
)
