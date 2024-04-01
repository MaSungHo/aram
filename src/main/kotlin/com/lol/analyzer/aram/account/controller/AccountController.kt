package com.lol.analyzer.aram.account.controller

import com.lol.analyzer.aram.account.entity.Account
import com.lol.analyzer.aram.account.service.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(
        private val accountService: AccountService
) {
    @GetMapping("/by-puuid/{puuid}")
    // TODO: apply DTO
    fun getAccount(@PathVariable("puuid") puuid: String): Account {
        return this.accountService.getAccountByPuuid(puuid)
    }
}