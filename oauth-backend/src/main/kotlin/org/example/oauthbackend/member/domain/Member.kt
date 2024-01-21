package org.example.oauthbackend.member.domain

import java.util.*
import org.example.oauthbackend.member.entity.MemberEntity

class Member(
    val id: UUID,
    val email: String,
    val nickname: String,
) {
    companion object {
        fun MemberEntity.toDomain(): Member =
            Member(id = this.id!!, email = this.email, nickname = this.nickname)
    }
}