package org.example.oauthbackend.member.repository

import java.util.UUID
import org.example.oauthbackend.member.entity.MemberEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : CoroutineCrudRepository<MemberEntity, UUID> {
    suspend fun findByEmail(email: String): MemberEntity?
    suspend fun findByNickname(email: String): MemberEntity?
}
