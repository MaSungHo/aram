package com.lol.analyzer.aram.account.application

import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.account.domain.Account
import com.lol.analyzer.aram.account.domain.AccountRepository
import com.lol.analyzer.aram.account.exception.NotFoundException
import com.lol.analyzer.aram.riot.domain.LolApi
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val riotApi: LolApi
) {
    fun getAccountByUuid(uuid: String): AccountResponse {
        val account = this.accountRepository.findByUuid(uuid) ?: throw NotFoundException(
                "No account with that uuid."
        )

        return AccountResponse.from(account)
    }

    fun getAccountByRiotInfo(
        gameName: String,
        tagLine: String
    ): AccountResponse {
        var account = this.accountRepository.findByGameNameAndTagLine(
            gameName,
            tagLine
        )

        if (account == null) {
            val accountResponse = riotApi.getAccountByRiotId(gameName, tagLine)
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
