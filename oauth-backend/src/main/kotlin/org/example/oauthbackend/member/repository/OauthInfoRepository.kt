package org.example.oauthbackend.member.repository

import org.example.oauthbackend.auth.domain.OauthType
import org.example.oauthbackend.member.entity.OauthId
import org.example.oauthbackend.member.entity.OauthInfoEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OauthInfoRepository : CoroutineCrudRepository<OauthInfoEntity, OauthId> {

    suspend fun findByOauthIdAndOauthType(oauthId: OauthId, oauthType: OauthType): OauthInfoEntity?
}
