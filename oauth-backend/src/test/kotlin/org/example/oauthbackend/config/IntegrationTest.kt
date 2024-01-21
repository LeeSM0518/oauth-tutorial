package org.example.oauthbackend.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@Target(AnnotationTarget.CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [PostgresqlInitializer::class])
annotation class IntegrationTest
