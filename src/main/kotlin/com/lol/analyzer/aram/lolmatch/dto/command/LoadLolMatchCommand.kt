package com.lol.analyzer.aram.lolmatch.dto.command

data class LoadLolMatchCommand(
    val uuid: String,
    val cursor: Long,
    val count: Int
)