package org.example.oauthbackend.auth.service

import org.example.oauthbackend.auth.client.naver.NaverOauthClient
import org.example.oauthbackend.auth.client.naver.NaverProfileClient
import org.example.oauthbackend.auth.client.naver.dto.OauthGrantType
import org.example.oauthbackend.auth.config.oauth.NaverOauthProperties
import org.example.oauthbackend.auth.controller.dto.LoginRequest
import org.example.oauthbackend.auth.exception.NAVER_OAUTH_LOGIN_FAIL_EXCEPTION
import org.example.oauthbackend.auth.exception.OauthLoginFailException
import org.example.oauthbackend.member.entity.OauthId
import org.springframework.stereotype.Service

@Service
class NaverOauthService(
    private val naverOauthClient: NaverOauthClient,
    private val naverProfileClient: NaverProfileClient,
    private val naverOauthProperties: NaverOauthProperties,
) {

    fun getOauthId(request: LoginRequest): OauthId = getOauthId(getToken(request))

    private fun getOauthId(token: String): OauthId =
        naverProfileClient.getOauthId(token).profile?.id
            ?: throw OauthLoginFailException(NAVER_OAUTH_LOGIN_FAIL_EXCEPTION)

    private fun getToken(request: LoginRequest): String =
        naverOauthClient
            .getToken(
                grantType = OauthGrantType.AUTHORIZATION_CODE.value,
                clientId = naverOauthProperties.clientId,
                clientSecret = naverOauthProperties.clientSecret,
                code = request.authorizationCode,
                state = naverOauthProperties.state
            )
            .takeIf { it.token != null }
            ?.token
            ?: throw OauthLoginFailException(NAVER_OAUTH_LOGIN_FAIL_EXCEPTION)
}