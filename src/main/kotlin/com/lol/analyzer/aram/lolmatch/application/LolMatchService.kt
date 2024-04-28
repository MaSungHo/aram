package com.lol.analyzer.aram.lolmatch.application

import com.lol.analyzer.aram.lolmatch.domain.LolMatch
import com.lol.analyzer.aram.lolmatch.domain.LolMatchCustomRepository
import com.lol.analyzer.aram.lolmatch.dto.command.LoadLolMatchCommand
import com.lol.analyzer.aram.riot.domain.LolApi
import org.springframework.stereotype.Service

@Service
class LolMatchService(
    private val lolMatchRepository: LolMatchCustomRepository,
    private val lolApi: LolApi,
) {
    fun loadLolMatchesByPuuid(loadLolMatchCommand: LoadLolMatchCommand): List<LolMatch> {
        // TODO(startIndex & count 개수만큼 불러오기)
        val lolMatches = this.lolMatchRepository.getLolMatchesByAccountPuuid(
            puuid = loadLolMatchCommand.puuid,
            cursor = loadLolMatchCommand.cursor,
            count = loadLolMatchCommand.count
        )

        // TODO count 개수만큼 잘 불러왔다면 바로 리턴
        if (lolMatches.size == loadLolMatchCommand.count) return lolMatches

        // TODO(매치 전체 개수와 마지막 matchId 를 불러오는 api 작성)
        var (totalCount, lastId) = this.lolMatchRepository.getLolMatchesCountAndLastLolMatchId(loadLolMatchCommand.puuid)
        var lolMatchIds = this.lolApi.getLolMatchesByPuuid(
            loadLolMatchCommand.puuid,
            totalCount,
            2 * loadLolMatchCommand.count // load double
        )

        if (lastId != null) { lolMatchIds = lolMatchIds.filter { it < lastId } }

        // TODO lolMatchIds 중 마지막 matchId 보다 적은 것 (오래된 것) 들 filter
        lolMatchIds.forEach {
            var lolMatchResponse = this.lolApi.getLolMatchByMatchId(it)
            println(it)
        }

        return lolMatches
    }
}
