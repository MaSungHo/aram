package com.lol.analyzer.aram.lolmatch.domain

import com.lol.analyzer.aram.common.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table
class AccountLolMatch(
    @ManyToOne()

    @Column(length = 20) // TODO enum
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
): BaseEntity() {
}
