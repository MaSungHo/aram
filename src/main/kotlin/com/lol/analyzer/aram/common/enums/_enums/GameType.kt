package com.lol.analyzer.aram.common.enums._enums

enum class GameType(val gameType: String, val description: String) {
    CUSTOM_GAME("CUSTOM_GAME", "Custom games"),
    TUTORIAL_GAME("TUTORIAL_GAME", "Tutorial games"),
    MATCHED_GAME("MATCHED_GAME", "all other games")
}
