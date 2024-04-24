package com.lol.analyzer.aram.lolmatch.application

import com.lol.analyzer.aram.lolmatch.domain.AccountLolMatchRepository
import com.lol.analyzer.aram.riot.domain.LolApi
import org.springframework.stereotype.Service

@Service
class LolMatchService(
    private val accountLolMatchRepository: AccountLolMatchRepository,
    private val lolApi: LolApi,
) {
    fun loadLolMatchesByPuuid(puuid: String): List<String> {
        val lolMatchIds = this.lolApi.getLolMatchesByPuuid(puuid)

        return lolMatchIds
    }
}
