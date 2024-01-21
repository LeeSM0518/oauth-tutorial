package org.example.oauthbackend.auth.service

import java.util.*
import org.example.oauthbackend.auth.domain.Authorization
import org.example.oauthbackend.auth.domain.RefreshToken
import org.example.oauthbackend.auth.domain.TokenGroup
import org.example.oauthbackend.member.domain.Member
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val jwtService: JwtService
) {

    suspend fun createTokenGroup(member: Member): TokenGroup =
        TokenGroup(
            accessToken = jwtService.createAccessToken(member.id),
            refreshToken = jwtService.createRefreshToken(member.id)
        )

    suspend fun reissueTokenGroup(refreshToken: RefreshToken): TokenGroup {
        val tokenSubject = jwtService.validateRefreshToken(refreshToken)
        val reissuedAccessToken = jwtService.createAccessToken(tokenSubject.memberId)
        val reissuedRefreshToken = jwtService.createRefreshToken(tokenSubject.memberId)
        return TokenGroup(reissuedAccessToken, reissuedRefreshToken)
    }

    suspend fun expireRefreshToken(memberId: UUID) {
        jwtService.expireRefreshToken(memberId)
    }

    suspend fun getMemberId(authorization: Authorization): UUID =
        jwtService
            .validateAccessToken(authorization.accessToken)
            .memberId

}
