package com.lol.analyzer.aram.riot.dto.response.lolmatch

data class RiotLolMatchParticipantResponse(
    val puuid: String,
    val lane: String,
    val physicalDamageDealt: Int,
    val physicalDamageDealtToChampions: Int,
    val physicalDamageTaken: Int,
    val magicDamageDealt: Int,
    val magicDamageDealtToChampions: Int,
    val magicDamageTaken: Int,
    val championName: String,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val win: Boolean,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int
) {
}
