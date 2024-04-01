package com.lol.analyzer.aram.account.dto

import com.lol.analyzer.aram.account.entity.Account

data class AccountResponse(
        val gameName: String,
        val tagLine: String,
) {
    companion object {
        fun from(account: Account): AccountResponse {
            return AccountResponse(account.gameName, account.tagLine)
        }
    }
}
