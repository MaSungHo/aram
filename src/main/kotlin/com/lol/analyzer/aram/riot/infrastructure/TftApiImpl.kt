package com.lol.analyzer.aram.riot.infrastructure

import com.lol.analyzer.aram.riot.domain.TftApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

// https://docs.spring.io/spring-framework/docs/6.0.0/reference/html/integration.html#rest-http-interface
@Configuration
class TftApiImpl(
    @Value("\${riot.tft-api-key}")
    private val apiKey: String,
): RiotApiImpl(apiKey) {
    @Bean
    fun tftApi(): TftApi {
        return HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(getWebClient()))
            .build()
            .createClient(TftApi::class.java)
    }
}