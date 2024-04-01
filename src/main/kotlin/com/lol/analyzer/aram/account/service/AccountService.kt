package com.lol.analyzer.aram.account.service

import com.lol.analyzer.aram.account.entity.Account
import com.lol.analyzer.aram.account.repository.AccountRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountRepository: AccountRepository
) {
    // TODO: apply DTO
    fun getAccountByPuuid(puuid: String): Account {
        return this.accountRepository.findByPuuid(puuid) ?: throw IllegalArgumentException(
                "No account with that puuid."
        )
    }
}
