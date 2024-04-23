package com.lol.analyzer.aram.common.enums._enums

enum class ErrorCode(val code: String) {
    // common
    UNDEFINED("0000"),
    RECORD_NOT_FOUND("0404"),

    // riot
    RIOT_RESPONSE_ERROR("9000")
}
