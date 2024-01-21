package org.example.oauthbackend.member.controller

import jakarta.validation.Valid
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
    private val memberService: MemberService
) {

    @PostMapping("/signup")
    suspend fun signUp(
        @RequestBody @Valid
        request: SignUpRequest
    ): SignUpResponse {
        val member = memberService.signUp(request)
        return SignUpResponse(
            memberId = member.id!!,
            nickname = member.nickname,
            email = member.email
        )
    }
}