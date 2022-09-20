package com.afshinshahriarifahliani.marvel_characters.data.api

import com.afshinshahriarifahliani.marvel_characters.data.model.MarvelApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {

    @GET("characters")
    suspend fun getCharacters(@Query("offset") offset: Int? = 0): Response<MarvelApiResponse>

    @GET("characters/{characterId}")
    suspend fun getSingleCharacterById(
        @Path("characterId")
        characterId: Int,
    ): Response<MarvelApiResponse>

    @GET("characters")
    suspend fun searchCharacter(
        @Query("nameStartsWith") query: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): Response<MarvelApiResponse>
}