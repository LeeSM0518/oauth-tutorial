package org.example.oauthbackend.auth.service

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import org.example.oauthbackend.auth.config.JwtConfigProperties
import org.example.oauthbackend.auth.domain.AccessToken
import org.example.oauthbackend.auth.domain.RefreshToken
import org.example.oauthbackend.auth.domain.TokenSubject
import org.example.oauthbackend.auth.entity.RefreshTokenEntity
import org.example.oauthbackend.auth.exception.CREATE_ACCESS_TOKEN_EXCEPTION
import org.example.oauthbackend.auth.exception.CREATE_REFRESH_TOKEN_EXCEPTION
import org.example.oauthbackend.auth.exception.CreateAccessTokenException
import org.example.oauthbackend.auth.exception.CreateRefreshTokenException
import org.example.oauthbackend.auth.exception.ExpiredTokenException
import org.example.oauthbackend.auth.exception.InvalidRefreshTokenException
import org.example.oauthbackend.auth.exception.NotFoundRefreshTokenException
import org.example.oauthbackend.auth.exception.UnsupportedTokenFormatException
import org.example.oauthbackend.auth.exception.ValidateTokenException
import org.example.oauthbackend.auth.repository.RefreshTokenRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class JwtService(
    private val refreshTokenRepository: RefreshTokenRepository,
    jwtConfigProperties: JwtConfigProperties,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    private val secretKey = jwtConfigProperties.secretKey.toByteArray()
    private val accessTokenExpirationHour = jwtConfigProperties.accessTokenExpirationHour.toLong()
    private val refreshTokenExpirationHour = jwtConfigProperties.refreshTokenExpirationHour.toLong()

    suspend fun createAccessToken(memberId: UUID): AccessToken =
        runCatching {
            createToken(memberId, accessTokenExpirationHour)
        }.getOrElse {
            logger.error(CREATE_ACCESS_TOKEN_EXCEPTION)
            logger.error(it.stackTraceToString())
            throw CreateAccessTokenException()
        }

    @Transactional
    suspend fun createRefreshToken(memberId: UUID): RefreshToken =
        runCatching {
            val token = createToken(memberId, refreshTokenExpirationHour)
            refreshTokenRepository
                .findByMemberId(memberId)
                .let { refreshToken ->
                    refreshToken
                        ?.update(token)
                        ?: RefreshTokenEntity(memberId = memberId, token = token)
                }
                .let { refreshToken ->
                    refreshTokenRepository.save(refreshToken)
                }
            token
        }.getOrElse {
            logger.error(CREATE_REFRESH_TOKEN_EXCEPTION)
            logger.error(it.stackTraceToString())
            throw CreateRefreshTokenException()
        }

    suspend fun validateAccessToken(token: AccessToken): TokenSubject = validateToken(secretKey, token)

    suspend fun validateRefreshToken(token: RefreshToken): TokenSubject =
        runCatching {
            val tokenSubject = validateToken(secretKey, token)
            validateRefreshToken(tokenSubject, token)
            tokenSubject
        }.getOrElse { exception ->
            logger.error("리프래시 토큰 검증을 실패했습니다")
            logger.error(exception.stackTraceToString())
            throw exception
        }

    suspend fun expireRefreshToken(memberId: UUID) {
        refreshTokenRepository.deleteByMemberId(memberId)
    }

    private suspend fun validateRefreshToken(
        tokenSubject: TokenSubject,
        token: RefreshToken
    ) {
        val foundRefreshToken: RefreshTokenEntity =
            refreshTokenRepository
                .findByMemberId(tokenSubject.memberId)
                ?: throw NotFoundRefreshTokenException()

        foundRefreshToken
            .takeIf { it.token == token }
            ?: throw InvalidRefreshTokenException()
    }


    private fun validateToken(secretKey: ByteArray, token: String): TokenSubject =
        runCatching {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey))
                .build()
                .parseClaimsJws(token)
                .body
                .subject
                .let { memberId -> TokenSubject(UUID.fromString(memberId)) }
        }.getOrElse { exception ->
            logger.error("토큰 검증을 실패했습니다")
            logger.error(exception.stackTraceToString())
            throw when (exception) {
                is UnsupportedJwtException -> UnsupportedTokenFormatException()
                is MalformedJwtException -> UnsupportedTokenFormatException()
                is SignatureException -> UnsupportedTokenFormatException()
                is IllegalArgumentException -> UnsupportedTokenFormatException()
                is ExpiredJwtException -> ExpiredTokenException()
                else -> ValidateTokenException()
            }
        }

    private fun createToken(memberId: UUID, expirationHour: Long): String {
        val now = LocalDateTime.now()
        return Jwts
            .builder()
            .setSubject(memberId.toString())
            .setIssuedAt(Date.from(now.atZone(UTC_TIME_ZONE).toInstant()))
            .setExpiration(Date.from(now.plusHours(expirationHour).atZone(UTC_TIME_ZONE).toInstant()))
            .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
            .compact()
    }

    companion object {
        private val UTC_TIME_ZONE = ZoneId.of("UTC")
    }
}