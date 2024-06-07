package com.lol.analyzer.aram.common.dto.response

import com.lol.analyzer.aram.common.enums._enums.ErrorCode

data class ExceptionResponse(
    val statusCode: Int,
    val error: ErrorCode,
    val message: String?,
) {
    val code: String
        get() { return this.error.code }
}