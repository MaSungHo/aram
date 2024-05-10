package com.lol.analyzer.aram.riot.dto.response.lolmatch

import com.lol.analyzer.aram.common.enums._enums.GameMode
import com.lol.analyzer.aram.common.enums._enums.GameType
import com.lol.analyzer.aram.common.enums._enums.Maps

data class RiotLolMatchInfoResponse(
    val gameStartTimestamp: Long,
    val gameEndTimestamp: Long,
    val gameMode: GameMode,
    val gameType: GameType,
    val mapId: Maps,
    val participants: List<RiotLolMatchParticipantResponse> = listOf()
) {
}
