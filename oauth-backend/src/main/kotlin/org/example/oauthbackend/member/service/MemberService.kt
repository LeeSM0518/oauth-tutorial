package org.example.oauthbackend.member.service

import org.example.oauthbackend.member.dto.SignUpRequest
import org.example.oauthbackend.auth.exception.DuplicateEmailException
import org.example.oauthbackend.auth.exception.DuplicateNicknameException
import org.example.oauthbackend.auth.exception.MEMBER_NOT_FOUND_BY_EMAIL_EXCEPTION
import org.example.oauthbackend.auth.exception.MemberNotFoundByEmailException
import org.example.oauthbackend.member.domain.Member
import org.example.oauthbackend.member.domain.Member.Companion.toDomain
import org.example.oauthbackend.member.entity.MemberEntity
import org.example.oauthbackend.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    suspend fun signUp(request: SignUpRequest): Member {
        validateRequest(request)
        val member = MemberEntity(email = request.email, nickname = request.nickname)
        val savedMember = memberRepository.save(member)
        return savedMember.toDomain()
    }

    suspend fun findByEmail(email: String): Member =
        memberRepository
            .findByEmail(email)
            ?.toDomain()
            ?: throw MemberNotFoundByEmailException("{\"email\":\"${email}\"}")

    private suspend fun validateRequest(request: SignUpRequest) {
        memberRepository
            .findByEmail(request.email)
            ?.let { throw DuplicateEmailException() }

        memberRepository
            .findByNickname(request.nickname)
            ?.let { throw DuplicateNicknameException() }
    }
}