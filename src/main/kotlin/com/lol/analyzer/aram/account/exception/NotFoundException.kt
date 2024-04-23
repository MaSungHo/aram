package com.lol.analyzer.aram.account.exception

import com.lol.analyzer.aram.common.enums._enums.ErrorCode


class NotFoundException(
    override val message: String,
    override var errorCode: ErrorCode = ErrorCode.RECORD_NOT_FOUND
): AccountException(message) {
}
