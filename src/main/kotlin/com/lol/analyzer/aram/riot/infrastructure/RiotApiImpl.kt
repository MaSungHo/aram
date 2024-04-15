package com.lol.analyzer.aram.riot.infrastructure

import com.lol.analyzer.aram.riot.domain.RiotApi
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

// https://docs.spring.io/spring-framework/docs/6.0.0/reference/html/integration.html#rest-http-interface
@Configuration
class RiotApiImpl {
    companion object {
        const val RIOT_API_URL = "https://asia.api.riotgames.com"
        const val TIMEOUT_MILLIS = 60000L
    }

    @Bean
    fun riotApi(): RiotApi {
        return HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(getWebClient()))
            .build()
            .createClient(RiotApi::class.java)
    }

    private fun getWebClient() = WebClient
        .builder()
        .baseUrl(RIOT_API_URL)
        .defaultHeader("X-Riot-Token", "riot-api-token")
        .clientConnector(ReactorClientHttpConnector(httpClient()))
        .build()

    private fun httpClient() = HttpClient
        .create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_MILLIS.toInt())
        .responseTimeout(Duration.ofMillis(TIMEOUT_MILLIS))
        .doOnConnected {
            it
                .addHandlerFirst(ReadTimeoutHandler(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                .addHandlerLast(ReadTimeoutHandler(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
        }
}