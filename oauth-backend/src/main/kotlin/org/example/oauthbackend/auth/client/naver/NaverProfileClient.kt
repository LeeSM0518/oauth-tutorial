package org.example.oauthbackend.auth.client.naver

import org.example.oauthbackend.auth.client.naver.dto.GetProfileResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "naverProfileClient", url = "\${naver.profileHost}")
interface NaverProfileClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["\${naver.profileUrl}"])
    fun getOauthId(@RequestHeader("Authorization") token: String): GetProfileResponse
}