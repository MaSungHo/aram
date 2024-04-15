package com.lol.analyzer.aram.account.dto

import com.lol.analyzer.aram.common.enums.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

data class ExceptionResponse(
    val statusCode: Int,
    val error: ErrorCode,
    val message: String?,
) {
    val code: String
        get() { return this.error.code }
}