package com.lol.analyzer.aram.account.dto

import com.lol.analyzer.aram.account.domain.Account

data class AccountResponse(
        val uuid: String?,
        val gameName: String,
        val tagLine: String,
) {
    companion object {
        fun from(account: Account): AccountResponse {
            return AccountResponse(account.uuid, account.gameName, account.tagLine)
        }
    }
}
