package org.example.oauthbackend.mebmer.controller

import io.netty.handler.codec.http.HttpResponseStatus.CONFLICT
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.example.oauthbackend.auth.domain.OauthInfo
import org.example.oauthbackend.auth.domain.OauthType
import org.example.oauthbackend.auth.service.JwtService
import org.example.oauthbackend.member.dto.SignUpRequest
import org.example.oauthbackend.member.dto.SignUpResponse
import org.example.oauthbackend.config.IntegrationTest
import org.example.oauthbackend.member.entity.MemberEntity
import org.example.oauthbackend.member.repository.MemberRepository
import org.example.oauthbackend.member.repository.OauthInfoRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@IntegrationTest
internal class MemberControllerTest @Autowired constructor(
    private val webTestClient: WebTestClient,
    private val memberRepository: MemberRepository,
    private val oauthInfoRepository: OauthInfoRepository,
    private val jwtService: JwtService,
) {

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        memberRepository.deleteAll()
        oauthInfoRepository.deleteAll()
    }

    @Test
    fun `네이버로 회원가입을 할 수 있다`(): Unit = runBlocking {
        postSignUp()
    }

    @Test
    fun `이미 존재하는 이메일로 회원가입할 경우 실패한다`(): Unit = runBlocking {
        postSignUp()

        val request = SignUpRequest(
            oauthId = "oauthId",
            nickname = "newNickname",
            oauthType = OauthType.NAVER
        )

        webTestClient
            .post()
            .uri("/api/members/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isEqualTo(CONFLICT.code())
    }

    @Test
    fun `이미 존재하는 닉네임으로 회원가입할 경우 실패한다`(): Unit = runBlocking {
        postSignUp()

        val request = SignUpRequest(
            oauthId = "newEmail",
            nickname = "nickname",
            oauthType = OauthType.NAVER
        )

        webTestClient
            .post()
            .uri("/api/members/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isEqualTo(CONFLICT.code())
    }

    private suspend fun postSignUp(): SignUpResponse {
        val request = SignUpRequest(
            oauthId = "oauthId",
            nickname = "nickname",
            oauthType = OauthType.NAVER
        )

        val response = webTestClient
            .post()
            .uri("/api/members/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody<SignUpResponse>()
            .returnResult()
            .responseBody!!

        assertThat(response.member.nickname).isEqualTo(request.nickname)
        assertThat(jwtService.validateAccessToken(response.tokenGroup.accessToken).memberId).isEqualTo(response.member.id)
        assertThat(jwtService.validateAccessToken(response.tokenGroup.refreshToken).memberId).isEqualTo(response.member.id)

        return response
    }
}
