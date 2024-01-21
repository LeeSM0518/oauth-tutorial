package org.example.oauthbackend.auth.repository

import java.util.UUID
import org.example.oauthbackend.auth.entity.RefreshTokenEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CoroutineCrudRepository<RefreshTokenEntity, UUID> {
    suspend fun findByMemberId(memberId: UUID): RefreshTokenEntity?

    suspend fun deleteByMemberId(memberId: UUID)
}