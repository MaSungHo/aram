package com.lol.analyzer.aram.account.dto

data class AccountDomain(
    val gameName: String,
    val tagLine: String
) {
    companion object {
        fun from(accountRequest: AccountCreateRequest): AccountDomain {
            return AccountDomain(accountRequest.gameName, accountRequest.tagLine)
        }
    }
}