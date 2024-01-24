package org.example.oauthbackend.member.service

import org.example.oauthbackend.auth.domain.OauthInfo
import org.example.oauthbackend.member.dto.SignUpRequest
import org.example.oauthbackend.auth.exception.DuplicateOauthException
import org.example.oauthbackend.auth.exception.DuplicateNicknameException
import org.example.oauthbackend.auth.exception.MemberNotFoundByEmailException
import org.example.oauthbackend.member.domain.Member
import org.example.oauthbackend.member.domain.Member.Companion.toDomain
import org.example.oauthbackend.member.entity.MemberEntity
import org.example.oauthbackend.member.entity.OauthInfoEntity
import org.example.oauthbackend.member.repository.MemberRepository
import org.example.oauthbackend.member.repository.OauthInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val oauthInfoRepository: OauthInfoRepository,
) {
    @Transactional
    suspend fun signUp(request: SignUpRequest): Member {
        validateRequest(request)
        val member = MemberEntity(nickname = request.nickname)
        val savedMember = memberRepository.save(member)
        val oauthInfo = OauthInfoEntity(
            oauthId = request.oauthId,
            oauthType = request.oauthType,
            memberId = savedMember.id!!
        )
        oauthInfoRepository.save(oauthInfo)
        return savedMember.toDomain()
    }

    suspend fun findByOauthInfo(oauthInfo: OauthInfo): Member {
        val foundOauthInfo = oauthInfoRepository
            .findByOauthIdAndOauthType(oauthInfo.oauthId, oauthInfo.oauthType)
            ?: throw MemberNotFoundByEmailException("{\"oauthId\":\"${oauthInfo.oauthId}\"}")

        return memberRepository
            .findById(foundOauthInfo.memberId)
            ?.toDomain()
            ?: throw MemberNotFoundByEmailException("{\"oauthId\":\"${foundOauthInfo.oauthId}\"}")
    }

    private suspend fun validateRequest(request: SignUpRequest) {
        oauthInfoRepository
            .findByOauthIdAndOauthType(request.oauthId, request.oauthType)
            ?.let { throw DuplicateOauthException() }

        memberRepository
            .findByNickname(request.nickname)
            ?.let { throw DuplicateNicknameException() }
    }
}