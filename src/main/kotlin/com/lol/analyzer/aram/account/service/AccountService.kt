package com.lol.analyzer.aram.account.service

import com.lol.analyzer.aram.account.dto.AccountCreateRequest
import com.lol.analyzer.aram.account.dto.AccountDomain
import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.account.entity.Account
import com.lol.analyzer.aram.account.repository.AccountRepository
import com.lol.analyzer.aram.riot.service.RiotApi
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val riotApi: RiotApi
) {
    fun getAccountByPuuid(puuid: String): AccountResponse {
        val account = this.accountRepository.findByPuuid(puuid) ?: throw IllegalArgumentException(
                "No account with that puuid."
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
