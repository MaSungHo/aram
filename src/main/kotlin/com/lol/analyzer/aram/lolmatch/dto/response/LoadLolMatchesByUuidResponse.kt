package com.lol.analyzer.aram.lolmatch.dto.response

data class LoadLolMatchesByUuidResponse(
    val data: List<LoadLolMatchResponse> = listOf(),
    val count: Int = data.size
) {
}
