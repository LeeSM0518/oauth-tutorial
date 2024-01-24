package org.example.oauthbackend.auth.domain

import org.example.oauthbackend.member.entity.OauthId

data class OauthInfo(
    val oauthId: OauthId,
    val oauthType: OauthType
)