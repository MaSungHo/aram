package com.lol.analyzer.aram.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
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
                .title("API Test")
                .description("ARAM LoL Analyzer API")
                .version("1.0.0")
    }
}
