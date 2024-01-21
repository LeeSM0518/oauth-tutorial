package org.example.oauthbackend.auth.domain

class Authorization(
    origin: String
) {
    val accessToken: AccessToken = origin.replace("Bearer ", "")
}
