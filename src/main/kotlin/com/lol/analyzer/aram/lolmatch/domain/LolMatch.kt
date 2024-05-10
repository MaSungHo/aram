package com.lol.analyzer.aram.lolmatch.domain

import com.lol.analyzer.aram.common.domain.BaseEntity
import com.lol.analyzer.aram.common.enums._enums.GameMode
import com.lol.analyzer.aram.common.enums._enums.GameType
import com.lol.analyzer.aram.common.enums._enums.Maps
import com.lol.analyzer.aram.common.enums.converter.*
import jakarta.persistence.*

@Entity
@Table(
    name = "lol_matches",
    indexes = [
        Index(name = "idx_match_id", columnList = "matchId", unique = true),
        Index(name = "idx_game_start_timestamp", columnList = "gameStartTimestamp"),
    ]
)
@Convert(converter = MapsConverter::class, attributeName = "mapId")
@Convert(converter = GameModeConverter::class, attributeName = "gameMode")
@Convert(converter = GameTypeConverter::class, attributeName = "gameType")
class LolMatch(
    @Column(name = "match_id", length = 30)
    var matchId: String,

    @Column(name = "map_id")
    var mapId: Maps,

    @Column(name = "game_mode", length = 30)
    var gameMode: GameMode,

    @Column(name = "game_type", length = 20)
    var gameType: GameType,

    @Column(name = "game_start_timestamp")
    var gameStartTimestamp: Long,

    @Column(name = "game_end_timestamp")
    var gameEndTimestamp: Long,
): BaseEntity() {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lolMatch", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    private var _accountLolMatches: MutableList<AccountLolMatch> = mutableListOf()
    val accountLolMatches: List<AccountLolMatch>
        get() = _accountLolMatches.toList()

    fun addAccountLolMatch(accountLolMatch: AccountLolMatch) {
        this._accountLolMatches.add(accountLolMatch)
    }
}
