package com.lol.analyzer.aram.lolmatch.presentation

import com.lol.analyzer.aram.lolmatch.application.LolMatchService
import com.lol.analyzer.aram.lolmatch.dto.request.LoadLolMatchRequest
import com.lol.analyzer.aram.lolmatch.dto.response.LoadLolMatchesByUuidResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lol/matches")
@Tag(name = "LolMatch", description = "LolMatch API")
class LolMatchController(
    private val lolMatchService: LolMatchService
) {
    @PostMapping("/by-uuid")
    @Operation(description = "UUID 로 LoL Match 불러오기")
    fun loadLolMatchesByUuid(@RequestBody loadLolMatchRequest: LoadLolMatchRequest): LoadLolMatchesByUuidResponse {
        return this.lolMatchService.loadLolMatchesByUuid(loadLolMatchRequest.toCommand())
    }
}
