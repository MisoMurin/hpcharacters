package com.example.hpcharacters.characters.data.mappers

import com.example.hpcharacters.characters.data.remote.HpCharacterDto
import com.example.hpcharacters.characters.domain.HpCharacter

fun HpCharacterDto.toHpCharacter(): HpCharacter {
    return HpCharacter(
        name = name,
        actor = actor,
        image = image,
    )
 }
