package org.example.oauthbackend.auth.service

import org.example.oauthbackend.auth.controller.dto.OauthLoginRequest
import org.example.oauthbackend.auth.domain.OauthType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val naverOauthService: NaverOauthService,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun getEmail(request: OauthLoginRequest): String =
        runCatching {
            when (request.oauthType) {
                OauthType.NAVER -> naverOauthService.getEmail(request)
            }
        }.getOrElse { exception ->
            logger.error("OAuth 인증을 실패했습니다.")
            logger.error(exception.stackTraceToString())
            throw exception
        }
}
