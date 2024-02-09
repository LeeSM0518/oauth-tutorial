package org.example.oauthbackend.auth.client.kakao.dto

import com.fasterxml.jackson.annotation.JsonProperty
import feign.form.FormProperty
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class GetKakaoTokenRequest(
    @FormProperty("grant_type")
//    @JsonProperty("grant_type")
    val grantType: String,
    @FormProperty("client_id")
//    @JsonProperty("client_id")
    val clientId: String,
    @FormProperty("redirect_uri")
//    @JsonProperty("redirect_uri")
    val redirectUri: String,
    @FormProperty("code")
//    @JsonProperty("code")
    val code: String,
) {
    fun toMap(): MultiValueMap<String, String> {
        val map = LinkedMultiValueMap<String, String>()
        map.add("grant_type", grantType)
        map.add("client_id" ,clientId)
        map.add("redirect_uri", redirectUri)
        map.add("code", code)
        return map
    }

    override fun toString(): String {
        return "grant_type=${grantType}&client_id=${clientId}&redirect_uri=${redirectUri}&code=${code}"
    }
}
