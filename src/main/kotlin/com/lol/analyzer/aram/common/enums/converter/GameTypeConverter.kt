package com.lol.analyzer.aram.common.enums.converter

import com.lol.analyzer.aram.common.enums._enums.GameType
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class GameTypeConverter: AttributeConverter<GameType, String> {
    override fun convertToDatabaseColumn(attribute: GameType): String = attribute.gameType

    override fun convertToEntityAttribute(dbData: String): GameType = GameType.entries.first { it.gameType == dbData }
}
