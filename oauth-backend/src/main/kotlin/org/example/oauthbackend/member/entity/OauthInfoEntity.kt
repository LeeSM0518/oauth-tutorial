package org.example.oauthbackend.member.entity

import java.time.Instant
import java.util.UUID
import org.example.oauthbackend.auth.domain.OauthType
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

typealias OauthId = String

@Table(value = "oauth_info")
data class OauthInfoEntity(
    @Id
    val oauthId: OauthId,
    val oauthType: OauthType,
    val memberId: UUID,
    val registeredDate: Instant? = null,
) : Persistable<String> {
    override fun getId(): String = oauthId

    override fun isNew(): Boolean = registeredDate == null
}
