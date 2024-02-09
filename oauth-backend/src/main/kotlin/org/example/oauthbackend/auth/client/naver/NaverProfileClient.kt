package org.example.oauthbackend.auth.client.naver

import org.example.oauthbackend.auth.client.naver.dto.GetNaverProfileResponse
import org.example.oauthbackend.auth.config.feign.BasicFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    name = "naverProfileClient",
    url = "\${naver.profileHost}",
    configuration = [BasicFeignConfig::class]
)
interface NaverProfileClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["\${naver.profileUrl}"])
    fun getOauthId(@RequestHeader("Authorization") token: String): GetNaverProfileResponse
}