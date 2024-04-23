package com.lol.analyzer.aram.common.enums.converter

import com.lol.analyzer.aram.common.enums._enums.GameMode
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class GameModeConverter: AttributeConverter<GameMode, String> {
    override fun convertToDatabaseColumn(attribute: GameMode): String = attribute.gameMode

    override fun convertToEntityAttribute(dbData: String): GameMode = GameMode.entries.first { it.gameMode == dbData }
}
