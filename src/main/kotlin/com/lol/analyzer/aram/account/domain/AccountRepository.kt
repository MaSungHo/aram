package com.lol.analyzer.aram.account.domain

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long> {
    fun findByUuid(uuid: String): Account?
    fun findByGameNameAndTagLine(gameName: String, tagLine: String): Account?
}
