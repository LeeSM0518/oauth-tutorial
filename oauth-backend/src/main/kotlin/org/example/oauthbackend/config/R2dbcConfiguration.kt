package org.example.oauthbackend.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
@EnableR2dbcRepositories
class R2dbcConfiguration {

    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer =
        ConnectionFactoryInitializer()
            .apply {
                setConnectionFactory(connectionFactory)
                setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
            }
}
