package org.example.oauthbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
class OauthBackendApplication

fun main(args: Array<String>) {
	runApplication<OauthBackendApplication>(*args)
}
