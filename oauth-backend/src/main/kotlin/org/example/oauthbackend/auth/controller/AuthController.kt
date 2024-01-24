package org.example.oauthbackend.auth.controller

import jakarta.validation.Valid
import org.example.oauthbackend.auth.domain.TokenGroup
import org.example.oauthbackend.auth.controller.dto.LoginRequest
import org.example.oauthbackend.auth.controller.dto.LoginResponse
import org.example.oauthbackend.auth.controller.dto.LogoutRequest
import org.example.oauthbackend.auth.controller.dto.LogoutResponse
import org.example.oauthbackend.auth.controller.dto.ReissueRequest
import org.example.oauthbackend.auth.controller.dto.ReissueResponse
import org.example.oauthbackend.auth.domain.Authorization
import org.example.oauthbackend.auth.domain.OauthInfo
import org.example.oauthbackend.auth.service.OauthService
import org.example.oauthbackend.auth.service.TokenService
import org.example.oauthbackend.member.domain.Member
import org.example.oauthbackend.member.service.MemberService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val oauthService: OauthService,
    private val tokenService: TokenService,
    private val memberService: MemberService
) {
    @PostMapping("/login")
    suspend fun login(
        @RequestBody @Valid
        request: LoginRequest
    ): LoginResponse {
        val oauthInfo: OauthInfo = oauthService.getOauthInfo(request)
        val member: Member = memberService.findByOauthInfo(oauthInfo)
        val tokenGroup: TokenGroup = tokenService.createTokenGroup(member)
        return LoginResponse(
            member = member,
            tokenGroup = tokenGroup
        )
    }

    @Transactional
    @PostMapping("/logout")
    suspend fun logout(
        @RequestBody @Valid
        request: LogoutRequest
    ): LogoutResponse {
        val memberId = tokenService.getMemberId(Authorization(request.refreshToken))
        tokenService.expireRefreshToken(memberId)
        return LogoutResponse()
    }

    @PostMapping("/reissue")
    suspend fun reissue(
        @RequestBody @Valid
        request: ReissueRequest
    ): ReissueResponse {
        val tokenGroup = tokenService.reissueTokenGroup(request.refreshToken)
        return ReissueResponse(tokenGroup)
    }
}