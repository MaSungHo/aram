package com.lol.analyzer.aram.lolmatch.domain

import org.springframework.data.jpa.repository.JpaRepository

interface LolMatchCustomRepository {
    fun getLolMatchesByAccountPuuid(puuid: String, cursor: Long, count : Int): List<LolMatch>

    fun getLolMatchesCountAndLastLolMatchId(puuid: String): Pair<Int, String?>
}
