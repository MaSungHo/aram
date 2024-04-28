package com.lol.analyzer.aram.lolmatch.dto.command

data class LoadLolMatchCommand(
    val puuid: String,
    val cursor: Long,
    val count: Int
)