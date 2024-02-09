package org.example.oauthbackend.auth.client.naver.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetNaverProfileResponse(
    @JsonProperty("resultcode")
    val resultCode: String,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("response")
    val profile: Profile?,
) {

    data class Profile(
        @JsonProperty("id")
        val id: String?,
        @JsonProperty("email")
        val email: String?,
    )
}