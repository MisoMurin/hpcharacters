package com.example.hpcharacters.characters.data.remote

import retrofit2.http.GET

interface HpCharacterApi {

    @GET("characters")
    suspend fun getCharacters(): List<HpCharacterDto>

    companion object {
        const val BASE_URL = "https://hp-api.onrender.com/api/"
    }
}
