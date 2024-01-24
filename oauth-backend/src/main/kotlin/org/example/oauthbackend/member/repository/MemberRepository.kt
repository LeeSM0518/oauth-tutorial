package org.example.oauthbackend.member.repository

import java.util.UUID
import org.example.oauthbackend.auth.domain.OauthType
import org.example.oauthbackend.member.entity.MemberEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : CoroutineCrudRepository<MemberEntity, UUID> {
    suspend fun findByNickname(email: String): MemberEntity?
}
