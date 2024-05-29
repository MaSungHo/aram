package com.lol.analyzer.aram.account.application

import com.lol.analyzer.aram.account.domain.AccountRepository
import com.lol.analyzer.aram.account.dto.AccountResponse
import com.lol.analyzer.aram.account.exception.NotFoundException
import com.lol.analyzer.aram.account.presentation.fixture.AccountDataFactory
import com.lol.analyzer.aram.riot.domain.LolApi
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.mock.mockito.MockBean

class AccountServiceSpec: BehaviorSpec({
    val accountRepository = mockk<AccountRepository>()
    val riotApi = mockk<LolApi>()

    val accountService = AccountService(accountRepository, riotApi)

    Given("getAccountByUuid 에서") {
        When("uuid 를 가진 account 가 있을때") {
            val uuid = "found-uuid"
            val account = AccountDataFactory.account()
            every { accountRepository.findByUuid(uuid) } returns account

            Then("AccountResponse 를 반환") {
                accountService.getAccountByUuid(uuid) shouldBe AccountResponse.from(account)
            }
        }

        When("uuid 를 가진 account 가 없을때") {
            val uuid = "not-found-uuid"
            every { accountRepository.findByUuid(uuid) } returns null

            Then("NotFoundException 이 발생") {
                shouldThrow<NotFoundException> { accountService.getAccountByUuid(uuid) }
            }
        }
    }
})
