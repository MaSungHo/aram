package com.lol.analyzer.aram.account.domain

import com.lol.analyzer.aram.common.entity.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.util.UUID

@Entity(name = "accounts")
class Account(
        @Column
        @Comment("Riot 계정의 puuid")
        var puuid: String,

        @Column(name = "game_name")
        var gameName: String,

        @Column(name = "tag_line")
        var tagLine: String,

        @Column
        @Comment("Aram 서비스 내에서의 uuid")
        var uuid: String? = null,
):BaseEntity() {
        @PrePersist()
        fun setUuid() {
                this.uuid = UUID.randomUUID().toString()
        }
}
