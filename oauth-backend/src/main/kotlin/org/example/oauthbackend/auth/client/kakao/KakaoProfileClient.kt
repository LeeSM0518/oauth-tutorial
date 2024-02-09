package org.example.oauthbackend.auth.client.kakao

import org.example.oauthbackend.auth.client.kakao.dto.GetKakaoProfileResponse
import org.example.oauthbackend.auth.config.feign.BasicFeignConfig
import org.springframework.cloud.openfeign.FeignClient
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
        method = [RequestMethod.GET], value = ["\${kakao.profileUrl}"],
        produces = ["application/x-www-form-urlencoded;charset=utf-8"]
    )
    fun getOauthId(
        @RequestHeader("Authorization") token: String,
    ): GetKakaoProfileResponse
}
