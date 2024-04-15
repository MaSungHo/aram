package com.lol.analyzer.aram.riot.domain

import com.lol.analyzer.aram.riot.dto.RiotAccountResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange

interface RiotApi {
    @GetExchange("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    fun getAccountByRiotId(@PathVariable gameName: String, @PathVariable tagLine: String): RiotAccountResponse
}
