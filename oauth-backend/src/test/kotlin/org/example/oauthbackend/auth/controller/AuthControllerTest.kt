package org.example.oauthbackend.auth.controller

import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.example.oauthbackend.auth.entity.RefreshTokenEntity
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

@IntegrationTest
internal class AuthControllerTest @Autowired constructor(
    private val webTestClient: WebTestClient,
    private val memberRepository: MemberRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtService: JwtService
) {

    lateinit var memberId: UUID

    @BeforeEach
    fun setUp(): Unit = runBlocking {
        val member = memberRepository.save(MemberEntity(email = "email", nickname = "nickname"))
        memberId = member.id!!
        refreshTokenRepository.save(RefreshTokenEntity(memberId = memberId, token = "token"))
    }

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        memberRepository.deleteAll()
        refreshTokenRepository.deleteAll()
    }

    @Test
    fun `로그아웃을 할 수 있다`(): Unit = runBlocking {
        val accessToken = jwtService.createAccessToken(memberId)

        webTestClient
            .post()
            .uri("/api/auth/logout")
            .accept(MediaType.APPLICATION_JSON)
            .header(AUTHORIZATION, "Bearer $accessToken")
            .exchange()
            .expectStatus().isOk
    }

}