package com.lol.analyzer.aram.lolmatch.dto.response

import com.lol.analyzer.aram.lolmatch.domain.AccountLolMatch
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "Load Account Lol Match", description = "LoL Account Match 불러오기")
data class LoadAccountLolMatchResponse(
    @Schema(description = "라인", example = "TOP")
    val lane: String,
    @Schema(description = "가한 총 물리데미지")
    val physicalDamageDealt: Int,
    @Schema(description = "챔피언에 가한 물리데미지")
    val physicalDamageDealtToChampions: Int,
    @Schema(description = "받은 물리데미지")
    val physicalDamageTaken: Int,
    @Schema(description = "가한 마법데미지")
    val magicDamageDealt: Int,
    @Schema(description = "챔피언에 가한 마법데미지")
    val magicDamageDealtToChampions: Int,
    @Schema(description = "받은 마법데미지")
    val magicDamageTaken: Int,
    @Schema(description = "플레이한 챔피언")
    val championName: String,
    @Schema(description = "킬 수")
    val kills: Int,
    @Schema(description = "데스 수")
    val deaths: Int,
    @Schema(description = "어시스트 수")
    val assists: Int,
    @Schema(description = "게임 승리 여부")
    val win: Boolean,
    @Schema(description = "첫번째 아이템")
    val item0: Int,
    @Schema(description = "두번째 아이템")
    val item1: Int,
    @Schema(description = "세번째 아이템")
    val item2: Int,
    @Schema(description = "네번째 아이템")
    val item3: Int,
    @Schema(description = "다섯번째 아이템")
    val item4: Int,
    @Schema(description = "여섯번째 아이템")
    val item5: Int,
) {
    companion object {
        fun from(accountLolMatch: AccountLolMatch) = LoadAccountLolMatchResponse(
            lane = accountLolMatch.lane,
            physicalDamageDealt = accountLolMatch.physicalDamageDealt,
            physicalDamageDealtToChampions = accountLolMatch.physicalDamageDealtToChampions,
            physicalDamageTaken = accountLolMatch.physicalDamageTaken,
            magicDamageDealt = accountLolMatch.magicDamageDealt,
            magicDamageDealtToChampions = accountLolMatch.magicDamageDealtToChampions,
            magicDamageTaken = accountLolMatch.magicDamageTaken,
            championName = accountLolMatch.championName,
            kills = accountLolMatch.kills,
            deaths = accountLolMatch.deaths,
            assists = accountLolMatch.assists,
            win = accountLolMatch.win,
            item0 = accountLolMatch.item0,
            item1 = accountLolMatch.item1,
            item2 = accountLolMatch.item2,
            item3 = accountLolMatch.item3,
            item4 = accountLolMatch.item4,
            item5 = accountLolMatch.item5,
        )
    }
}
