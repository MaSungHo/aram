package com.lol.analyzer.aram.common.enums.converter

import com.lol.analyzer.aram.common.enums._enums.Maps
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class MapsConverter: AttributeConverter<Maps, Int> {
    override fun convertToDatabaseColumn(attribute: Maps): Int = attribute.mapId

    override fun convertToEntityAttribute(dbData: Int): Maps = Maps.entries.first { it.mapId == dbData }
}
