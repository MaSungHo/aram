package com.lol.analyzer.aram.account.application

import com.lol.analyzer.aram.account.dto.AccountCreateRequest
import com.lol.analyzer.aram.account.dto.AccountDomain
import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.account.domain.Account
import com.lol.analyzer.aram.account.domain.AccountRepository
import com.lol.analyzer.aram.riot.domain.RiotApi
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val riotApi: RiotApi
) {
    fun getAccountByUuid(uuid: String): AccountResponse {
        val account = this.accountRepository.findByUuid(uuid) ?: throw IllegalArgumentException(
                "No account with that uuid."
        )

        return AccountResponse.from(account)
    }

    fun create(createRequest: AccountCreateRequest): AccountResponse {
        val accountDomain = AccountDomain.from(createRequest)

        var account = this.accountRepository.findByGameNameAndTagLine(
            accountDomain.gameName,
            accountDomain.tagLine
        )

        if (account == null) {
            val accountResponse = riotApi.getAccountByRiotId(createRequest.gameName, createRequest.tagLine)
            account = this.accountRepository.save(
                Account(
                    puuid = accountResponse.puuid,
                    gameName = accountResponse.gameName!!,
                    tagLine = accountResponse.tagLine!!
                )
            )
        }

        return AccountResponse.from(account);
    }
}
