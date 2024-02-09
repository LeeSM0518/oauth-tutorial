package org.example.oauthbackend.auth.client.google.dto

class GetGoogleTokenRequest(
    val grantType: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
    val code: String,
) {
    override fun toString(): String {
        return "grant_type=${grantType}&client_id=${clientId}&client_secret=${clientSecret}" +
                "&redirect_uri=${redirectUri}&code=${code}"
    }
}