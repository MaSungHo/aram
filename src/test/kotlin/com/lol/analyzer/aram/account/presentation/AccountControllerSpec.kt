package com.lol.analyzer.aram.account.presentation

import com.lol.analyzer.aram.account.application.AccountService
import com.lol.analyzer.aram.account.fixture.AccountDataFactory
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.reactive.function.client.WebClientResponseException

@WebMvcTest(AccountController::class)
class AccountControllerSpec(
    @Autowired private val mockMvc: MockMvc,
    @MockkBean private val accountService: AccountService
): BehaviorSpec() {
    init {
        Given("getAccountByUuid") {
            val uuid = "random-uuid-test"

            When("uuid 에 해당하는 Account 가 존재할 때") {
                val data = AccountDataFactory.uuidResponse(uuid)
                every { accountService.getAccountByUuid(uuid) } returns data

                Then("AccountResponse 를 반환") {
                    mockMvc.get("/api/accounts/by-uuid/${uuid}")
                        .andExpect {
                            status { MockMvcResultMatchers.status().isOk }
                            content { data }
                        }
                }
            }

            When("uuid 에 해당하는 Account 가 없을 때") {
                val exception = WebClientResponseException(404, "test", null, null, null)
                every { accountService.getAccountByUuid(uuid) } throws exception

                Then("ExceptionResponse 를 반환") {
                    mockMvc.get("/api/accounts/by-uuid/${uuid}")
                        .andExpect {
                            status { MockMvcResultMatchers.status().isNotFound }
                            content { AccountDataFactory.exceptionResponse(exception) }
                        }
                }
            }
        }

        Given("getAccountByRiotInfo") {
            val gameName = "testName"
            val tagLine = "testTagLine"

            When("gameName 과 tagLine 을 받았을 때") {
                val data = AccountDataFactory.riotInfoResponse(gameName, tagLine)
                every { accountService.getAccountByRiotInfo(gameName, tagLine) } returns data

                Then("AccountResponse 를 반환") {
                    mockMvc.get("/by-riot-info/${gameName}/${tagLine}")
                        .andExpect {
                            status { MockMvcResultMatchers.status().isOk }
                            content { data }
                        }
                }
            }

            When("gameName 과 tagLine 에 해당하는 Account 가 없을 때") {
                val exception = WebClientResponseException(404, "test", null, null, null)
                every { accountService.getAccountByRiotInfo(gameName, tagLine) } throws exception

                Then("ExceptionResponse 를 반환") {
                    mockMvc.get("/by-riot-info/${gameName}/${tagLine}")
                        .andExpect {
                            status { MockMvcResultMatchers.status().isNotFound }
                            content { AccountDataFactory.exceptionResponse(exception) }
                        }
                }
            }
        }
    }
}
