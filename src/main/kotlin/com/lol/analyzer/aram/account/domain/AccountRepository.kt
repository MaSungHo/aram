package com.lol.analyzer.aram.account.domain

import com.lol.analyzer.aram.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface AccountRepository: JpaRepository<Account, Long> {
    fun findByUuid(uuid: String): Account?
    fun findByGameNameAndTagLine(gameName: String, tagLine: String): Account?
}
