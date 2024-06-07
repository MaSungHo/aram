package com.lol.analyzer.aram.account.fixture

import com.lol.analyzer.aram.account.domain.Account
import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.common.dto.response.ExceptionResponse
import com.lol.analyzer.aram.common.enums._enums.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.web.reactive.function.client.WebClientResponseException

class AccountDataFactory {
    companion object {
        fun account(
            puuid: String = "puuid",
            gameName: String = "Test user",
            tagLine: String = "KR1",
            uuid: String = "random-uuid",
        ) = Account(puuid, gameName, tagLine, uuid)

        fun uuidResponse(uuid: String) = accountResponse(uuid = uuid)

        fun riotInfoResponse(gameName: String, tagLine: String) = accountResponse(gameName = gameName, tagLine = tagLine)

        fun exceptionResponse(e: WebClientResponseException) = ResponseEntity(ExceptionResponse(e.statusCode.value(), ErrorCode.RIOT_RESPONSE_ERROR, e.message), e.statusCode)

        private fun accountResponse(
            puuid: String = "puuid",
            gameName: String = "Test user",
            tagLine: String = "KR1",
            uuid: String = "uuid"
        ): AccountResponse {
            val account = Account(puuid, gameName, tagLine, uuid)
            return AccountResponse.from(account)
        }
    }
}
