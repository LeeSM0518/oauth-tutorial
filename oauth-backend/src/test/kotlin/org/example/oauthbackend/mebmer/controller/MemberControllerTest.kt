package org.example.oauthbackend.mebmer.controller

import io.netty.handler.codec.http.HttpResponseStatus.CONFLICT
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.example.oauthbackend.member.dto.SignUpRequest
import org.example.oauthbackend.member.dto.SignUpResponse
import org.example.oauthbackend.config.IntegrationTest
import org.example.oauthbackend.member.entity.MemberEntity
import org.example.oauthbackend.member.repository.MemberRepository
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
) {

    lateinit var expectedMember: MemberEntity

    @BeforeEach
    fun setUp(): Unit = runBlocking {
        expectedMember = memberRepository.save(MemberEntity(nickname = "nickname", email = "email"))
    }

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        memberRepository.deleteAll()
    }

    @Test
    fun `회원가입을 할 수 있다`(): Unit = runBlocking {
        val request = SignUpRequest(
            email = "newEmail",
            nickname = "newNickname"
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

        assertThat(response.email).isEqualTo(request.email)
        assertThat(response.nickname).isEqualTo(request.nickname)
    }

    @Test
    fun `이미 존재하는 이메일로 회원가입할 경우 실패한다`(): Unit = runBlocking {
        val request = SignUpRequest(
            email = "email",
            nickname = "newNickname"
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
        val request = SignUpRequest(
            email = "newEmail",
            nickname = "nickname"
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

}