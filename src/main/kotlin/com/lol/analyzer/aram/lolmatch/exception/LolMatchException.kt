package com.lol.analyzer.aram.lolmatch.exception

import com.lol.analyzer.aram.common.enums._enums.ErrorCode

open class LolMatchException(
    override val message: String,
    open var errorCode: ErrorCode = ErrorCode.UNDEFINED,
): Exception(message) {
}
