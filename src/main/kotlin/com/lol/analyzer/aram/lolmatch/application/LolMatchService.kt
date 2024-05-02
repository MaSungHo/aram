package com.lol.analyzer.aram.lolmatch.application

import com.lol.analyzer.aram.lolmatch.domain.AccountLolMatch
import com.lol.analyzer.aram.lolmatch.domain.LolMatch
import com.lol.analyzer.aram.lolmatch.domain.LolMatchCustomRepository
import com.lol.analyzer.aram.lolmatch.dto.command.LoadLolMatchCommand
import com.lol.analyzer.aram.riot.domain.LolApi
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class LolMatchService(
    private val lolMatchRepository: LolMatchCustomRepository,
    private val lolApi: LolApi,
) {
    fun loadLolMatchesByPuuid(loadLolMatchCommand: LoadLolMatchCommand): List<LolMatch> {
        val lolMatches = this.lolMatchRepository.getLolMatchesByAccountPuuid(
            puuid = loadLolMatchCommand.puuid,
            cursor = loadLolMatchCommand.cursor,
            count = loadLolMatchCommand.count
        )

        if (lolMatches.size == loadLolMatchCommand.count) return lolMatches

        val (totalCount, lastId) = this.lolMatchRepository.getLolMatchesCountAndLastLolMatchId(loadLolMatchCommand.puuid)
        var lolMatchIds = this.lolApi.getLolMatchesByPuuid(
            loadLolMatchCommand.puuid,
            totalCount,
            2 * loadLolMatchCommand.count // load double
        )
        if (lastId != null) { lolMatchIds = lolMatchIds.filter { it < lastId } }

        lolMatchIds.forEach {
            val (metadata, info) = this.lolApi.getLolMatchByMatchId(it)
            val participant = info.participants.find { it.puuid == loadLolMatchCommand.puuid } ?: throw NotFoundException()

            val lolMatch = LolMatch(
                matchId = metadata.matchId,
                mapId = info.mapId,
                gameMode = info.gameMode,
                gameType = info.gameType,
                gameEndTimestamp = info.gameEndTimestamp,
                gameStartTimestamp = info.gameStartTimestamp
            )

//            val accountLolMatch = AccountLolMatch(
//                lane = participant.lane,
//                physicalDamageDealt = participant.physicalDamageDealt,
//            )
        }

        return lolMatches
    }
}
