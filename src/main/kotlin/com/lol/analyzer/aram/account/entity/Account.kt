package com.lol.analyzer.aram.account.entity

import jakarta.persistence.*

@Entity(name = "accounts")
class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column
        var puuid: String,

        @Column(name = "game_name")
        var gameName: String,

        @Column(name = "tag_line")
        var tagLine: String,
) {
}
