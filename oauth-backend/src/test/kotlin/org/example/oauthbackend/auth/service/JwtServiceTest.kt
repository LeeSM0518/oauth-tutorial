package org.example.oauthbackend.auth.service

import org.example.oauthbackend.config.IntegrationTest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.example.oauthbackend.auth.repository.RefreshTokenRepository
import org.example.oauthbackend.member.entity.MemberEntity
import org.example.oauthbackend.member.repository.MemberRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
internal class JwtServiceTest @Autowired constructor(
    val jwtService: JwtService,
    val memberRepository: MemberRepository,
    val refreshTokenRepository: RefreshTokenRepository
) {

    lateinit var expectedMember: MemberEntity

    @BeforeEach
    fun setUp(): Unit = runBlocking {
        expectedMember = memberRepository.save(MemberEntity(nickname = "nickname", email = "email"))
    }

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        memberRepository.deleteAll()
        refreshTokenRepository.deleteAll()
    }

    @Test
    fun `액세스 토큰을 생성하고 검증할 수 있다`(): Unit = runBlocking {
        val expectedMemberId = expectedMember.id!!
        val createdAccessToken = jwtService.createAccessToken(expectedMemberId)

        val result = jwtService.validateAccessToken(createdAccessToken)

        assertThat(result.memberId).isEqualTo(expectedMemberId)
    }

    @Test
    fun `리프레시 토큰을 생성하고 검증할 수 있다`(): Unit = runBlocking {
        val expectedMemberId = expectedMember.id!!
        val createdRefreshToken = jwtService.createRefreshToken(expectedMemberId)

        val result = jwtService.validateRefreshToken(createdRefreshToken)

        assertThat(result.memberId).isEqualTo(expectedMemberId)
    }
}
