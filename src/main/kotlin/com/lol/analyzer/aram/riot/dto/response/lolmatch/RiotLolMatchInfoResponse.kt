package com.lol.analyzer.aram.riot.dto.response.lolmatch

import com.lol.analyzer.aram.common.enums._enums.GameMode
import com.lol.analyzer.aram.common.enums._enums.GameType

data class RiotLolMatchInfoResponse(
    val gameStartTimestamp: Long,
    val gameEndTimestamp: Long,
    val gameMode: GameMode,
    val gameType: GameType,
    val mapId: Int,
    val participants: List<RiotLolMatchParticipantResponse> = listOf()
) {
}
