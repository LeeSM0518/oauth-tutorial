package org.example.oauthbackend.auth.service

import org.example.oauthbackend.auth.client.google.GoogleOauthClient
import org.example.oauthbackend.auth.client.google.GoogleProfileClient
import org.example.oauthbackend.auth.client.google.dto.GetGoogleTokenRequest
import org.example.oauthbackend.auth.client.naver.dto.OauthGrantType
import org.example.oauthbackend.auth.config.oauth.GoogleOauthProperties
import org.example.oauthbackend.auth.controller.dto.LoginRequest
import org.example.oauthbackend.auth.exception.GOOGLE_OAUTH_LOGIN_FAIL_EXCEPTION
import org.example.oauthbackend.auth.exception.OauthLoginFailException
import org.example.oauthbackend.member.entity.OauthId
import org.springframework.stereotype.Service

@Service
class GoogleOauthService(
    private val googleOauthClient: GoogleOauthClient,
    private val googleProfileClient: GoogleProfileClient,
    private val googleOauthProperties: GoogleOauthProperties,
) {

    fun getOauthId(request: LoginRequest): OauthId = getOauthId(getToken(request))

    private fun getOauthId(token: String): OauthId =
        googleProfileClient.getOauthId(token).id
            ?: throw OauthLoginFailException(GOOGLE_OAUTH_LOGIN_FAIL_EXCEPTION)

    private fun getToken(request: LoginRequest): String =
        googleOauthClient
            .getToken(
                GetGoogleTokenRequest(
                    grantType = OauthGrantType.AUTHORIZATION_CODE.value,
                    clientId = googleOauthProperties.clientId,
                    clientSecret = googleOauthProperties.clientSecret,
                    redirectUri = googleOauthProperties.redirectUri,
                    code = request.authorizationCode
                ).toString()
            )
            .takeIf { it.accessToken != null }
            ?.let { "Bearer ${it.accessToken}" }
            ?: throw OauthLoginFailException(GOOGLE_OAUTH_LOGIN_FAIL_EXCEPTION)

}
