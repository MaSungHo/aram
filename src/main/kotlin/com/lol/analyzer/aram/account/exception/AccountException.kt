package com.lol.analyzer.aram.account.exception

import com.lol.analyzer.aram.common.enums.ErrorCode

open class AccountException(
    override val message: String,
    open var errorCode: ErrorCode = ErrorCode.UNDEFINED,
): Exception(message) {
}
