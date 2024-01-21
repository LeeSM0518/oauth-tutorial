package org.example.oauthbackend.auth.service

import org.example.oauthbackend.auth.client.naver.NaverOauthClient
import org.example.oauthbackend.auth.client.naver.NaverProfileClient
import org.example.oauthbackend.auth.client.naver.dto.NaverGrantType
import org.example.oauthbackend.auth.config.NaverOauthProperties
import org.example.oauthbackend.auth.controller.dto.OauthLoginRequest
import org.example.oauthbackend.auth.exception.NAVER_OAUTH_LOGIN_FAIL_EXCEPTION
import org.example.oauthbackend.auth.exception.OauthLoginFailException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class NaverOauthService(
    private val naverOauthClient: NaverOauthClient,
    private val naverProfileClient: NaverProfileClient,
    private val naverOauthProperties: NaverOauthProperties,
) {

    fun getEmail(request: OauthLoginRequest): String = getEmail(getToken(request))

    private fun getEmail(token: String) = (naverProfileClient
        .getEmail(token)
        .email
        ?: throw OauthLoginFailException(NAVER_OAUTH_LOGIN_FAIL_EXCEPTION))

    private fun getToken(request: OauthLoginRequest) = (naverOauthClient
        .getToken(
            grantType = NaverGrantType.AUTHORIZATION_CODE.value,
            clientId = naverOauthProperties.clientId,
            clientSecret = naverOauthProperties.clientSecret,
            code = request.authorizationCode,
            state = naverOauthProperties.state
        )
        .takeIf { it.token != null }
        ?.token
        ?: throw OauthLoginFailException(NAVER_OAUTH_LOGIN_FAIL_EXCEPTION))
}