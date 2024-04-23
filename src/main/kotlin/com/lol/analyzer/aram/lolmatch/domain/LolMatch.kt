package com.lol.analyzer.aram.lolmatch.domain

import com.lol.analyzer.aram.common.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(
    name = "lol_matches",
    indexes = [
        Index(name = "idx_match_id", columnList = "matchId", unique = true),
        Index(name = "idx_game_start_timestamp", columnList = "gameStartTimestamp"),
    ]
)
class LolMatch(
    @Column(name = "match_id", length = 30)
    var matchId: String,

    @Column(name = "map_id") // TODO enum
    var mapId: Int,

    @Column(name = "game_mode", length = 30) // TODO enum
    var gameMode: String,

    @Column(name = "game_type", length = 20) // TODO enum
    var gameType: String,

    @Column(name = "game_start_timestamp")
    var gameStartTimestamp: Long,

    @Column(name = "game_end_timestamp")
    var gameEndTimestamp: Long,
): BaseEntity() {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lolMatch")
    private var _accountLolMatches: MutableList<AccountLolMatch> = mutableListOf()
    val accountLolMatches: List<AccountLolMatch>
        get() = _accountLolMatches.toList()
}
