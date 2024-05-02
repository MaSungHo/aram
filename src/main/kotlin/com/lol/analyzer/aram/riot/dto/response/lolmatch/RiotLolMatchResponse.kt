package com.lol.analyzer.aram.riot.dto.response.lolmatch

data class RiotLolMatchResponse(
    val metadata: RiotLolMatchMetadataResponse,
    val info: RiotLolMatchInfoResponse
) {
}