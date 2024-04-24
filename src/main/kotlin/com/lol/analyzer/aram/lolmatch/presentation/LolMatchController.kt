package com.lol.analyzer.aram.lolmatch.presentation

import com.lol.analyzer.aram.lolmatch.application.LolMatchService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lol/matches")
@Tag(name = "LolMatch", description = "LolMatch API")
class LolMatchController(
    private val lolMatchService: LolMatchService
) {
    @GetMapping("by-puuid/{puuid}")
    @Operation(description = "PUUID 로 Match IDs 조회 (임시)")
    fun loadLolMatchIdsByPuuid(@PathVariable("puuid") puuid: String): List<String> {
        return this.lolMatchService.loadLolMatchesByPuuid(puuid)
    }
}
