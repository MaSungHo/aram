package com.lol.analyzer.aram.account.repository

import com.lol.analyzer.aram.account.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Long> {
    // TODO: apply DTO
    fun findByPuuid(puuid: String): Account {
        return this.findByPuuid(puuid)
    }
}
