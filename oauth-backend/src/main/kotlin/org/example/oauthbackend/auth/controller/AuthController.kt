package org.example.oauthbackend.auth.controller

import jakarta.validation.Valid
import org.example.oauthbackend.auth.domain.TokenGroup
import org.example.oauthbackend.auth.controller.dto.LoginRequest
import org.example.oauthbackend.auth.controller.dto.LoginResponse
import org.example.oauthbackend.auth.controller.dto.LogoutResponse
import org.example.oauthbackend.auth.controller.dto.ReissueRequest
import org.example.oauthbackend.auth.controller.dto.ReissueResponse
import org.example.oauthbackend.auth.domain.Authorization
import org.example.oauthbackend.auth.service.OauthService
import org.example.oauthbackend.auth.service.TokenService
import org.example.oauthbackend.member.domain.Member
import org.example.oauthbackend.member.service.MemberService
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
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
        val email: String = oauthService.getEmail(request)
        val member: Member = memberService.findByEmail(email)
        val tokenGroup: TokenGroup = tokenService.createTokenGroup(member)
        return LoginResponse(
            member = member,
            tokenGroup = tokenGroup
        )
    }

    @Transactional
    @PostMapping("/logout")
    suspend fun logout(
        @RequestHeader(AUTHORIZATION)
        authorization: String
    ): LogoutResponse {
        val memberId = tokenService.getMemberId(Authorization(authorization))
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