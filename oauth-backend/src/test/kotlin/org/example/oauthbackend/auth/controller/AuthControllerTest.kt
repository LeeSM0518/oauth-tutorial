package org.example.oauthbackend.auth.controller

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.example.oauthbackend.auth.controller.dto.LogoutRequest
import org.example.oauthbackend.auth.controller.dto.ReissueRequest
import org.example.oauthbackend.auth.controller.dto.ReissueResponse
import org.example.oauthbackend.auth.repository.RefreshTokenRepository
import org.example.oauthbackend.auth.service.JwtService
import org.example.oauthbackend.config.IntegrationTest
import org.example.oauthbackend.member.entity.MemberEntity
import org.example.oauthbackend.member.repository.MemberRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@IntegrationTest
internal class AuthControllerTest @Autowired constructor(
    private val webTestClient: WebTestClient,
    private val memberRepository: MemberRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtService: JwtService,
) {

    lateinit var expectedMember: MemberEntity

    @BeforeEach
    fun setUp(): Unit = runBlocking {
        expectedMember = memberRepository.save(MemberEntity(email = "email", nickname = "nickname"))
    }

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        memberRepository.deleteAll()
        refreshTokenRepository.deleteAll()
    }

    @Test
    fun `로그아웃을 할 수 있다`(): Unit = runBlocking {
        val refreshToken = jwtService.createRefreshToken(expectedMember.id!!)

        webTestClient
            .post()
            .uri("/api/auth/logout")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(LogoutRequest(refreshToken))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `토큰을 재발급 받을 수 있다`(): Unit = runBlocking {
        val expectedRefreshToken = jwtService.createRefreshToken(expectedMember.id!!)

        val refreshToken = webTestClient
            .post()
            .uri("/api/auth/reissue")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(ReissueRequest(expectedRefreshToken))
            .exchange()
            .expectStatus().isOk
            .expectBody<ReissueResponse>()
            .returnResult()
            .responseBody!!
            .tokenGroup
            .refreshToken

        val refreshTokenEntity = refreshTokenRepository.findByMemberId(expectedMember.id!!)!!
        assertThat(refreshTokenEntity.token).isEqualTo(refreshToken)
    }

}