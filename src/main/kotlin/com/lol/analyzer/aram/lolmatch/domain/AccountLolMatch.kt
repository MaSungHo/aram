package com.lol.analyzer.aram.lolmatch.domain

import com.lol.analyzer.aram.account.domain.Account
import com.lol.analyzer.aram.common.domain.BaseEntity
import jakarta.persistence.*

// https://spoqa.github.io/2022/08/16/kotlin-jpa-entity.html
@Entity
@Table(name = "account_lol_matches")
class AccountLolMatch(
    @Column(length = 20)
    var lane: String,

    @Column(name = "physical_damage_dealt")
    var physicalDamageDealt: Int,

    @Column(name = "physical_damage_dealt_to_champions")
    var physicalDamageDealtToChampions: Int,

    @Column(name = "physical_damage_taken")
    var physicalDamageTaken: Int,

    @Column(name = "magic_damage_dealt")
    var magicDamageDealt: Int,

    @Column(name = "magic_damage_dealt_to_champions")
    var magicDamageDealtToChampions: Int,

    @Column(name = "magic_damage_taken")
    var magicDamageTaken: Int,

    @Column(name = "champion_name", length = 20)
    var championName: String,

    @Column
    var kills: Int,

    @Column
    var deaths: Int,

    @Column
    var assists: Int,

    @Column
    var win: Boolean,

    @Column(name = "item_0")
    var item0: Int,

    @Column(name = "item_1")
    var item1: Int,

    @Column(name = "item_2")
    var item2: Int,

    @Column(name = "item_3")
    var item3: Int,

    @Column(name = "item_4")
    var item4: Int,

    @Column(name = "item_5")
    var item5: Int,

    account: Account,

    lolMatch: LolMatch,
): BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lol_match_id", nullable = false)
    var lolMatch: LolMatch = lolMatch

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    var account: Account = account
}
