package org.example.oauthbackend.auth.client.naver

import org.example.oauthbackend.auth.client.naver.dto.GetTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "naverOauthClient", url = "\${naver.oauthHost}")
interface NaverOauthClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["\${naver.tokenUrl}"])
    fun getToken(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("code") code: String,
        @RequestParam("state") state: String,
    ): GetTokenResponse

}