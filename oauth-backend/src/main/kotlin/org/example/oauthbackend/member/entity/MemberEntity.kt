package org.example.oauthbackend.member.entity

import java.time.Instant
import java.util.*
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(value = "member")
data class MemberEntity(
    @Id
    @Column("member_id")
    @get:JvmName("memberId")
    val id: UUID? = null,
    val nickname: String,
    val registeredDate: Instant = Instant.now(),
    val modifiedDate: Instant = registeredDate
): Persistable<UUID> {
    override fun getId(): UUID? = id

    override fun isNew(): Boolean = id == null
}