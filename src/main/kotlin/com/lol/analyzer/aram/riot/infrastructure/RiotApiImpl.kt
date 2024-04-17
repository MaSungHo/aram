package com.lol.analyzer.aram.riot.infrastructure

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

abstract class RiotApiImpl(
    private val apiKey: String,
) {
    companion object {
        const val RIOT_API_URL = "https://asia.api.riotgames.com"
        const val TIMEOUT_MILLIS = 60000L
    }

    open fun getWebClient(): WebClient {
        return WebClient
            .builder()
            .baseUrl(RiotApiImpl.RIOT_API_URL)
            .defaultHeader("X-Riot-Token", apiKey)
            .clientConnector(ReactorClientHttpConnector(httpClient()))
            .build()
    }

    private fun httpClient() = HttpClient
        .create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, RiotApiImpl.TIMEOUT_MILLIS.toInt())
        .responseTimeout(Duration.ofMillis(RiotApiImpl.TIMEOUT_MILLIS))
        .doOnConnected {
            it
                .addHandlerFirst(ReadTimeoutHandler(RiotApiImpl.TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                .addHandlerLast(ReadTimeoutHandler(RiotApiImpl.TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
        }
}
