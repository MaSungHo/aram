package com.lol.analyzer.aram.account.presentation

import com.lol.analyzer.aram.account.application.AccountService
import com.lol.analyzer.aram.account.domain.Account
import com.lol.analyzer.aram.account.dto.AccountResponse
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
@WebMvcTest(AccountController::class)
class AccountControllerSpec(
    @Autowired val mockMvc: MockMvc,
): BehaviorSpec() {
    @MockBean
    private val accountService = mockk<AccountService>()

    init {
        every { accountService.getAccountByUuid("uuid") } returns AccountResponse.from(account)

        Given("getAccountByUuid") {
            When("uuid 를 받았을 때") {
                Then("AccountResponse 를 반환") {
                    mockMvc.get("/api/accounts/by-uuid/uuid")
                        .andExpect {
                            status { MockMvcResultMatchers.status().isOk }
                            content { AccountResponse.from(account) }
                        }
                }
            }
        }
    }

    companion object {
        private val account = Account(
            puuid = "puuid",
            gameName = "Test user",
            tagLine = "KR1",
            uuid = "uuid"
        )
    }
}
