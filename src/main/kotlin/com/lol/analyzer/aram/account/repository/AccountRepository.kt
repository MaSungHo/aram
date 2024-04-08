package com.lol.analyzer.aram.account.repository

import com.lol.analyzer.aram.account.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Long> {
    fun findByPuuid(puuid: String): Account? {
        return this.findByPuuid(puuid)
    }

    fun findByGameNameAndTagLine(gameName: String, tagLine: String): Account? {
        return this.findByGameNameAndTagLine(gameName, tagLine)
    }
}
