package com.lol.analyzer.aram.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun tftAPI(): GroupedOpenApi {
        return GroupedOpenApi
            .builder()
            .group("TFT")
            .pathsToMatch("/api/tft/**")
            .build()
    }

    @Bean
    fun accountAPI(): GroupedOpenApi {
        return GroupedOpenApi
            .builder()
            .group("Account")
            .pathsToMatch("/api/accounts/**")
            .build()
    }

    @Bean
    fun leagueOfLegendAPI(): GroupedOpenApi {
        return GroupedOpenApi
            .builder()
            .group("League of Legend")
            .pathsToMatch("/api/lol/**")
            .build()
    }

    @Bean
    fun openAPI(): OpenAPI {
        val jwt = "JWT"
        val securityRequirement = SecurityRequirement().addList(jwt)
        val components = Components()
                .addSecuritySchemes(jwt, SecurityScheme()
                        .name(jwt)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat(jwt)
                )

        return OpenAPI()
                .components(components)
                .info(apiInfo())
                .addSecurityItem(securityRequirement)
    }

    private fun apiInfo(): Info {
        return Info()
                .title("ARAM API")
                .description("ARAM LoL Analyzer API")
                .version("1.0.0")
    }
}
