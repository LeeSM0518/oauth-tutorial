package org.example.oauthbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthBackendApplication

fun main(args: Array<String>) {
	runApplication<OauthBackendApplication>(*args)
}
