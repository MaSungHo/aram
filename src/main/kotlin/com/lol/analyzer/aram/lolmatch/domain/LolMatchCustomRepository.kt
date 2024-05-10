package com.lol.analyzer.aram.lolmatch.domain

interface LolMatchCustomRepository {
    fun getLolMatchByMatchId(matchId: String): LolMatch?

    fun getLolMatchesByAccountPuuid(puuid: String, cursor: Long, count : Int): List<LolMatch>

    fun getLolMatchesCountAndLastLolMatchId(puuid: String): Pair<Int, String?>

    fun save(lolMatch: LolMatch)
}
