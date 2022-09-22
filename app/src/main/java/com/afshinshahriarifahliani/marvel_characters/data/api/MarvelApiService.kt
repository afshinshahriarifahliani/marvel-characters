package com.afshinshahriarifahliani.marvel_characters.data.api

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.comics.ComicResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.events.EventsResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.series.SeriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.stories.StoriesResponse
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

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId")
        characterId: Int,
    ): Response<ComicResponse>

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId")
        characterId: Int,
    ): Response<SeriesResponse>

    @GET("characters/{characterId}/stories")
    suspend fun getCharacterStories(
        @Path("characterId")
        characterId: Int,
    ): Response<StoriesResponse>

    @GET("characters/{characterId}/events")
    suspend fun getCharacterEvents(
        @Path("characterId")
        characterId: Int,
    ): Response<EventsResponse>

    @GET("characters")
    suspend fun searchCharacterNameToStartWith(
        @Query("nameStartsWith") query: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): Response<MarvelApiResponse>
}