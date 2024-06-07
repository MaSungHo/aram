package com.lol.analyzer.aram.lolmatch.presentation

import com.lol.analyzer.aram.lolmatch.application.LolMatchService
import com.lol.analyzer.aram.lolmatch.exception.NotFoundException
import com.lol.analyzer.aram.lolmatch.fixture.LolMatchDataFactory
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.reactive.function.client.WebClientResponseException

@WebMvcTest(LolMatchController::class)
class LolMatchControllerSpec(
    @Autowired private val mockMvc: MockMvc,
    @MockkBean private val lolMatchService: LolMatchService
): BehaviorSpec({
//    Given("정상적인 loadLolMatchesByUuid 요청") {
//        When("Request 가 cursor = 2, count = 30 일 때") {
//
//        }
//    }

    Given("잘못된 loadLolMatchesByUuid 요청") {
        val request = LolMatchDataFactory.createRequest()
        val command = request.toCommand()

        When("WebClientResponseException 에러 발생시") {
            val exception = WebClientResponseException(400, "test", null, null, null)
            every { lolMatchService.loadLolMatchesByUuid(command) } throws exception

            Then("ExceptionResponse 를 반환") {
                mockMvc.post("/by-uuid", request)
                    .andExpect {
                        status { MockMvcResultMatchers.status().is4xxClientError }
                        content { LolMatchDataFactory.createWebExceptionResponse(exception) }
                    }
            }
        }

        When("NotFoundException 에러 발생시") {
            val exception = NotFoundException("Not Found")

            every { lolMatchService.loadLolMatchesByUuid(command) } throws exception

            Then("ExceptionResponse 를 반환") {
                mockMvc.post("/by-uuid", request)
                    .andExpect {
                        status { MockMvcResultMatchers.status().isNotFound }
                        content { LolMatchDataFactory.createExceptionResponse(exception) }
                    }
            }
        }
    }
})
