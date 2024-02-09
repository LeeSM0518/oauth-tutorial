package org.example.oauthbackend.auth.client.google

import org.example.oauthbackend.auth.client.google.dto.GetGoogleTokenResponse
import org.example.oauthbackend.auth.config.feign.FormFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    name = "googleOauthClient",
    url = "\${google.oauthHost}",
    configuration = [FormFeignConfig::class]
)
interface GoogleOauthClient {

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["\${google.tokenUrl}"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        headers = ["Content-Type: ${MediaType.APPLICATION_FORM_URLENCODED_VALUE}"]
    )
    fun getToken(request: String): GetGoogleTokenResponse

}
