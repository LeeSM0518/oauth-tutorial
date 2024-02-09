package org.example.oauthbackend.auth.client.kakao

import org.example.oauthbackend.auth.client.kakao.dto.GetKakaoProfileResponse
import org.example.oauthbackend.auth.config.feign.BasicFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    name = "kakaoProfileClient",
    url = "\${kakao.profileHost}",
    configuration = [BasicFeignConfig::class]
)
interface KakaoProfileClient {

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["\${kakao.profileUrl}"],
        headers = ["Content-Type: ${MediaType.APPLICATION_FORM_URLENCODED_VALUE}"]
    )
    fun getOauthId(
        @RequestHeader("Authorization") token: String,
    ): GetKakaoProfileResponse
}
