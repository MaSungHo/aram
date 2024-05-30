package com.lol.analyzer.aram.account.application

import com.lol.analyzer.aram.account.domain.AccountRepository
import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.account.exception.NotFoundException
import com.lol.analyzer.aram.account.fixture.AccountDataFactory
import com.lol.analyzer.aram.riot.domain.LolApi
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AccountServiceSpec: BehaviorSpec({
    val accountRepository = mockk<AccountRepository>()
    val riotApi = mockk<LolApi>()

    val accountService = AccountService(accountRepository, riotApi)

    Given("getAccountByUuid 에서") {
        When("uuid 를 가진 account 가 있을때") {
            Then("AccountResponse 를 반환") {
                val uuid = "found-uuid"
                val account = AccountDataFactory.account()
                every { accountRepository.findByUuid(uuid) } returns account

                accountService.getAccountByUuid(uuid) shouldBe AccountResponse.from(account)
            }
        }

        When("uuid 를 가진 account 가 없을때") {
            Then("NotFoundException 이 발생") {
                val uuid = "not-found-uuid"
                every { accountRepository.findByUuid(uuid) } returns null

                shouldThrow<NotFoundException> { accountService.getAccountByUuid(uuid) }
            }
        }
    }
})
