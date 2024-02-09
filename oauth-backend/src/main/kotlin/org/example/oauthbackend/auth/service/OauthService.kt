package org.example.oauthbackend.auth.service

import org.example.oauthbackend.auth.controller.dto.LoginRequest
import org.example.oauthbackend.auth.domain.OauthInfo
import org.example.oauthbackend.auth.domain.OauthType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val naverOauthService: NaverOauthService,
    private val kakaoOauthService: KakaoOauthService,
    private val googleOauthService: GoogleOauthService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun getOauthInfo(request: LoginRequest): OauthInfo =
        runCatching {
            when (request.oauthType) {
                OauthType.NAVER -> OauthInfo(
                    oauthId = naverOauthService.getOauthId(request),
                    oauthType = OauthType.NAVER
                )

                OauthType.KAKAO -> OauthInfo(
                    oauthId = kakaoOauthService.getOauthId(request),
                    oauthType = OauthType.KAKAO
                )

                OauthType.GOOGLE -> OauthInfo(
                    oauthId = googleOauthService.getOauthId(request),
                    oauthType = OauthType.GOOGLE
                )
            }
        }.getOrElse { exception ->
            logger.error("OAuth 인증을 실패했습니다.")
            logger.error(exception.stackTraceToString())
            throw exception
        }
}
