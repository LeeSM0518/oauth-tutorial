package org.example.oauthbackend.auth.config.feign

import feign.codec.Decoder
import feign.codec.Encoder
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean

class BasicFeignConfig {

    @Bean
    fun feignEncoder(): Encoder = SpringEncoder { HttpMessageConverters() }

    @Bean
    fun feignDecoder(): Decoder = SpringDecoder { HttpMessageConverters() }
}