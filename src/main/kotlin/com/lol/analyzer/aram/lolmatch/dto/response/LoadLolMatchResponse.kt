package com.lol.analyzer.aram.lolmatch.dto.response

import com.lol.analyzer.aram.common.enums._enums.GameMode
import com.lol.analyzer.aram.common.enums._enums.GameType
import com.lol.analyzer.aram.common.enums._enums.Maps
import com.lol.analyzer.aram.lolmatch.domain.LolMatch
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "Load Lol Match", description = "LoL Match 불러오기")
data class LoadLolMatchResponse(
    @Schema(description = "매치 ID")
    val matchId: String,
    @Schema(description = "맵 ID")
    val mapId: Maps,
    @Schema(description = "게임 모드")
    val gameMode: GameMode,
    @Schema(description = "게임 타입")
    val gameType: GameType,
    @Schema(description = "게임 시작시간")
    val gameStartTimestamp: Long,
    @Schema(description = "게임 종료시간")
    val gameEndTimestamp: Long,
    @Schema(description = "계정 정보")
    val accountLolMatches: List<LoadAccountLolMatchResponse> = listOf()
) {
    companion object {
        fun from(lolMatch: LolMatch) = LoadLolMatchResponse(
            matchId = lolMatch.matchId,
            mapId = lolMatch.mapId,
            gameMode = lolMatch.gameMode,
            gameType = lolMatch.gameType,
            gameStartTimestamp = lolMatch.gameStartTimestamp,
            gameEndTimestamp = lolMatch.gameEndTimestamp,
            accountLolMatches = lolMatch.accountLolMatches.map { LoadAccountLolMatchResponse.from(it) }
        )
    }
}
