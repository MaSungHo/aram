package com.lol.analyzer.aram.lolmatch.application

import com.lol.analyzer.aram.account.domain.AccountRepository
import com.lol.analyzer.aram.lolmatch.domain.AccountLolMatch
import com.lol.analyzer.aram.lolmatch.domain.LolMatch
import com.lol.analyzer.aram.lolmatch.domain.LolMatchCustomRepository
import com.lol.analyzer.aram.lolmatch.dto.command.LoadLolMatchCommand
import com.lol.analyzer.aram.lolmatch.dto.response.LoadLolMatchResponse
import com.lol.analyzer.aram.lolmatch.dto.response.LoadLolMatchesByUuidResponse
import com.lol.analyzer.aram.riot.domain.LolApi
import jakarta.transaction.Transactional
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClientResponseException

@Service
class LolMatchService(
    private val lolMatchRepository: LolMatchCustomRepository,
    private val accountRepository: AccountRepository, // TODO: refactor
    private val lolApi: LolApi,
) {
    @Transactional
    fun loadLolMatchesByUuid(loadLolMatchCommand: LoadLolMatchCommand): LoadLolMatchesByUuidResponse {
        val account = this.accountRepository.findByUuid(loadLolMatchCommand.uuid) ?: throw NotFoundException()
        val lolMatches = this.lolMatchRepository.getLolMatchesByAccountPuuid(
            puuid = account.puuid,
            cursor = loadLolMatchCommand.cursor,
            count = loadLolMatchCommand.count
        ).toMutableList()

        if (lolMatches.size != loadLolMatchCommand.count) {
            val (totalCount, lastId) = this.lolMatchRepository.getLolMatchesCountAndLastLolMatchId(account.puuid)
            var lolMatchIds = this.lolApi.getLolMatchesByPuuid(
                account.puuid,
                totalCount,
                2 * loadLolMatchCommand.count // load double
            )
            if (lastId != null) { lolMatchIds = lolMatchIds.filter { it < lastId } }

            lolMatchIds.forEach { matchId ->
                try {
                    val (metadata, info) = this.lolApi.getLolMatchByMatchId(matchId)

                    val participant = info.participants.find { p -> p.puuid == account.puuid } ?: throw NotFoundException()

                    val lolMatch = this.lolMatchRepository.getLolMatchByMatchId(matchId) ?: LolMatch(
                        matchId = metadata.matchId,
                        mapId = info.mapId,
                        gameMode = info.gameMode,
                        gameType = info.gameType,
                        gameEndTimestamp = info.gameEndTimestamp,
                        gameStartTimestamp = info.gameStartTimestamp
                    )

                    val accountLolMatch = AccountLolMatch(
                        account = account,
                        lolMatch = lolMatch,
                        lane = participant.lane,
                        physicalDamageDealt = participant.physicalDamageDealt,
                        physicalDamageDealtToChampions = participant.physicalDamageDealtToChampions,
                        physicalDamageTaken = participant.physicalDamageTaken,
                        magicDamageDealt = participant.magicDamageDealt,
                        magicDamageDealtToChampions = participant.magicDamageDealtToChampions,
                        magicDamageTaken = participant.magicDamageTaken,
                        championName = participant.championName,
                        kills = participant.kills,
                        deaths = participant.deaths,
                        assists = participant.assists,
                        win = participant.win,
                        item0 = participant.item0,
                        item1 = participant.item1,
                        item2 = participant.item2,
                        item3 = participant.item3,
                        item4 = participant.item4,
                        item5 = participant.item5
                    )
                    lolMatch.addAccountLolMatch(accountLolMatch)

                    this.lolMatchRepository.save(lolMatch)

                    if (lolMatches.size < loadLolMatchCommand.count) { lolMatches.add(lolMatch) }
                } catch (e: WebClientResponseException) {
                    if (e.statusCode != HttpStatusCode.valueOf(404)) {
                        throw e
                    }
                }
            }
        }

        val data = lolMatches.map { lolMatch ->  LoadLolMatchResponse.from(lolMatch) }

        return LoadLolMatchesByUuidResponse(
            count = data.size,
            data = data
        )
    }
}
