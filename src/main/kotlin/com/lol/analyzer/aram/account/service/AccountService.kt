package com.lol.analyzer.aram.account.service

import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.account.entity.Account
import com.lol.analyzer.aram.account.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountRepository: AccountRepository
) {
    fun getAccountByPuuid(puuid: String): AccountResponse {
        val account = this.accountRepository.findByPuuid(puuid) ?: throw IllegalArgumentException(
                "No account with that puuid."
        )

        return AccountResponse.from(account)
    }
}
