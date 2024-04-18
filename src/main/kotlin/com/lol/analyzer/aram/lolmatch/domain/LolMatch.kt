package com.lol.analyzer.aram.lolmatch.domain

import com.lol.analyzer.aram.common.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "lol_matches",
    indexes = [
        Index(name = "idx_match_id", columnList = "matchId", unique = true),
        Index(name = "idx_game_start_timestamp", columnList = "gameStartTimestamp"),
    ]
)
class LolMatch(
    @Column(name = "match_id")
    var matchId: String,

    @Column(name = "map_id")
    var mapId: Int,

    @Column(name = "game_mode")
    var gameMode: String,

    @Column(name = "game_type")
    var gameType: String,

    @Column(name = "game_start_timestamp")
    var gameStartTimestamp: Long,

    @Column(name = "game_end_timestamp")
    var gameEndTimestamp: Long
): BaseEntity() {
}
