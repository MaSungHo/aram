package com.lol.analyzer.aram.lolmatch.exception

import com.lol.analyzer.aram.common.dto.response.ExceptionResponse
import com.lol.analyzer.aram.common.enums._enums.ErrorCode
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.reactive.function.client.WebClientResponseException

@RestControllerAdvice(basePackages = ["com.lol.analyzer.aram.lolmatch"])
class LolMatchExceptionHandler {
    @ExceptionHandler(WebClientResponseException::class)
    fun webClientResponseException(e: WebClientResponseException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(ExceptionResponse(e.statusCode.value(), ErrorCode.RIOT_RESPONSE_ERROR, e.message), e.statusCode)
    }

    @ExceptionHandler(LolMatchException::class)
    fun lolMatchException(e: LolMatchException): ResponseEntity<ExceptionResponse> {
        val statusCode = HttpStatusCode.valueOf(400)
        return ResponseEntity(ExceptionResponse(statusCode.value(), e.errorCode, e.message), statusCode)
    }
}
