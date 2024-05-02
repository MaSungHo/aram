package com.lol.analyzer.aram.riot.dto.response.lolmatch

data class RiotLolMatchMetadataResponse(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String> = listOf()
) {
}
