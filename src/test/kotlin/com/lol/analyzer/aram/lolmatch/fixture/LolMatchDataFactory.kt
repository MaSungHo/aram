package com.lol.analyzer.aram.lolmatch.fixture

import com.lol.analyzer.aram.common.dto.response.ExceptionResponse
import com.lol.analyzer.aram.common.enums._enums.ErrorCode
import com.lol.analyzer.aram.common.enums._enums.GameMode
import com.lol.analyzer.aram.common.enums._enums.GameType
import com.lol.analyzer.aram.common.enums._enums.Maps
import com.lol.analyzer.aram.lolmatch.domain.LolMatch
import com.lol.analyzer.aram.lolmatch.dto.request.LoadLolMatchRequest
import com.lol.analyzer.aram.lolmatch.dto.response.LoadLolMatchResponse
import com.lol.analyzer.aram.lolmatch.dto.response.LoadLolMatchesByUuidResponse
import com.lol.analyzer.aram.lolmatch.exception.LolMatchException
import org.springframework.web.reactive.function.client.WebClientResponseException

class LolMatchDataFactory {
    companion object {
        fun createRequest(
            uuid: String = "uuid",
            cursor: Long = 0,
            count: Int = 10,
        ) = LoadLolMatchRequest(
            uuid = uuid,
            cursor = cursor,
            count = count
        )

        fun createExceptionResponse(e: LolMatchException) = ExceptionResponse(
            statusCode = 404,
            error = e.errorCode,
            message = e.message
        )

        fun createWebExceptionResponse(e: WebClientResponseException) = ExceptionResponse(
            statusCode = e.statusCode.value(),
            error = ErrorCode.RIOT_RESPONSE_ERROR,
            message = e.message
        )

        fun createLolMatch(
            matchId: String = "1",
            mapId: Maps = Maps.SUMMONERS_RIFT_SUMMER,
            gameMode: GameMode = GameMode.CLASSIC,
            gameType: GameType = GameType.CUSTOM_GAME,
            gameStartTimestamp: Long = 123456L,
            gameEndTimestamp: Long = 987654L,
        ) = LolMatch(
            matchId = matchId,
            mapId = mapId,
            gameMode = gameMode,
            gameType = gameType,
            gameStartTimestamp = gameStartTimestamp,
            gameEndTimestamp = gameEndTimestamp,
        )

        fun createLoadLolMatchesByUuidResponse(count: Int): LoadLolMatchesByUuidResponse {
            val result: MutableList<LolMatch> = mutableListOf()

            repeat(count) {
                result.add(createLolMatch())
            }

            return LoadLolMatchesByUuidResponse(
                result.map { LoadLolMatchResponse.from(it) }
            )
        }
    }
}
