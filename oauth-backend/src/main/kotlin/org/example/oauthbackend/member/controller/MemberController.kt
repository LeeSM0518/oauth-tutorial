package org.example.oauthbackend.member.controller

import jakarta.validation.Valid
import org.example.oauthbackend.auth.domain.TokenGroup
import org.example.oauthbackend.auth.service.JwtService
import org.example.oauthbackend.member.dto.SignUpRequest
import org.example.oauthbackend.member.dto.SignUpResponse
import org.example.oauthbackend.member.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
    private val jwtService: JwtService,
) {

    @PostMapping("/signup")
    suspend fun signUp(
        @RequestBody @Valid
        request: SignUpRequest,
    ): SignUpResponse {
        val member = memberService.signUp(request)
        val accessToken = jwtService.createAccessToken(member.id)
        val refreshToken = jwtService.createRefreshToken(member.id)
        return SignUpResponse(
            member = member,
            tokenGroup = TokenGroup(accessToken, refreshToken)
        )
    }
}