package com.example.hpcharacters.characters.data.remote

import javax.inject.Inject

class HpCharactersRepository
@Inject
constructor (
    private val hpCharactersApi: HpCharacterApi,
) {

    suspend fun getHpCharacters() = hpCharactersApi.getCharacters()
}
