package com.lol.analyzer.aram.account.presentation

import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.account.application.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account", description = "LoL Account API")
class AccountController(
        private val accountService: AccountService
) {
    @GetMapping("/by-uuid/{uuid}")
    @Operation(description = "UUID 로 Account 조회")
    fun getAccountByUuid(@PathVariable("uuid") uuid: String): AccountResponse {
        return this.accountService.getAccountByUuid(uuid)
    }

    @GetMapping("/by-riot-info/{gameName}/{tagLine}")
    @Operation(description = "Game Name & Tag Line 으로 Account 조회")
    fun getAccountByRiotInfo(
        @PathVariable("gameName") gameName: String,
        @PathVariable("tagLine") tagLine: String
    ): AccountResponse {
        return this.accountService.getAccountByRiotInfo(gameName, tagLine)
    }

}