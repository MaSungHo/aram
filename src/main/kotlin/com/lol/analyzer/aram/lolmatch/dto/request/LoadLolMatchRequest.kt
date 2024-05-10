package com.lol.analyzer.aram.lolmatch.dto.request

import com.lol.analyzer.aram.lolmatch.dto.command.LoadLolMatchCommand
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "Load Lol Match Request", description = "LoL Match 불러오기 요청")
data class LoadLolMatchRequest(
    @Schema(description = "Account 의 lol puuid", example = "puuid", required = true)
    val uuid: String,
    @Schema(description = "커서 ID", example = "0", required = false)
    val cursor: Long = 0,
    @Schema(description = "데이터 개수", example = "0", required = false)
    val count: Int = 20,
) {
    fun toCommand() = LoadLolMatchCommand(
        uuid = this.uuid,
        cursor = this.cursor,
        count = this.count
    )
}
