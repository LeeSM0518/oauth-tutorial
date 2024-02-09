package org.example.oauthbackend.auth.service

import org.example.oauthbackend.auth.client.kakao.KakaoOauthClient
import org.example.oauthbackend.auth.client.kakao.KakaoProfileClient
import org.example.oauthbackend.auth.client.kakao.dto.GetKakaoTokenRequest
import org.example.oauthbackend.auth.client.naver.dto.OauthGrantType
import org.example.oauthbackend.auth.config.oauth.KakaoOauthProperties
import org.example.oauthbackend.auth.controller.dto.LoginRequest
import org.example.oauthbackend.auth.exception.KAKAO_OAUTH_LOGIN_FAIL_EXCEPTION
import org.example.oauthbackend.auth.exception.OauthLoginFailException
import org.example.oauthbackend.member.entity.OauthId
import org.springframework.stereotype.Service

@Service
class KakaoOauthService(
    private val kakaoOauthClient: KakaoOauthClient,
    private val kakaoProfileClient: KakaoProfileClient,
    private val kakaoOauthProperties: KakaoOauthProperties,
) {

    fun getOauthId(request: LoginRequest): OauthId = getOauthId(getToken(request))

    private fun getOauthId(token: String): OauthId =
        kakaoProfileClient.getOauthId(token).id?.toString()
            ?: throw OauthLoginFailException(KAKAO_OAUTH_LOGIN_FAIL_EXCEPTION)

    private fun getToken(request: LoginRequest): String =
        kakaoOauthClient
            .getToken(
                GetKakaoTokenRequest(
                    grantType = OauthGrantType.AUTHORIZATION_CODE.value,
                    clientId = kakaoOauthProperties.clientId,
                    redirectUri = kakaoOauthProperties.redirectUrl,
                    code = request.authorizationCode,
                ).toString()
            )
            .takeIf { it.accessToken != null }
            ?.let { "Bearer ${it.accessToken}" }
            ?: throw OauthLoginFailException(KAKAO_OAUTH_LOGIN_FAIL_EXCEPTION)
}