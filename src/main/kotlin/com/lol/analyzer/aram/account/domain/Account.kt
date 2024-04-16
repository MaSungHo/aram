package com.lol.analyzer.aram.account.domain

import com.lol.analyzer.aram.common.domain.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.util.UUID

@Entity
@Table(
        name = "accounts",
        indexes = [
                Index(name = "idx_uuid", columnList = "uuid", unique = true),
                Index(name = "idx_game_name_and_tag_line", columnList = "gameName, tagLine", unique = true)
        ]
)
class Account(
        @Column(unique = true)
        @Comment("Riot 계정의 puuid")
        var puuid: String,

        // https://support-leagueoflegends.riotgames.com/hc/en-us/articles/360041788533-Riot-ID-FAQ
        @Column(name = "game_name", length = 20)
        var gameName: String,

        @Column(name = "tag_line", length = 10)
        var tagLine: String,

        @Column(unique = true)
        @Comment("Aram 서비스 내에서의 uuid")
        var uuid: String? = null,
):BaseEntity() {
        @PrePersist()
        fun setUuid() {
                this.uuid = UUID.randomUUID().toString()
        }
}
