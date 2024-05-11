package com.lol.analyzer.aram.lolmatch.dto.response

data class LoadLolMatchesByUuidResponse(
    val count: Int,
    val data: List<LoadLolMatchResponse> = listOf()
) {
}
