package com.afshinshahriarifahliani.marvel_characters.domain.repository

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.data.model.comics.ComicResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.events.EventsResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.series.SeriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.stories.StoriesResponse
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MarvelRepository {
    suspend fun getAllCharacters(offset: Int?): Resource<MarvelApiResponse>
    suspend fun getSingleCharacterById(characterId:Int):Resource<MarvelApiResponse>
    suspend fun getCharacterComics(characterId:Int): Resource<ComicResponse>
    suspend fun getCharacterSeries(characterId:Int): Resource<SeriesResponse>
    suspend fun getCharacterEvents(characterId:Int): Resource<EventsResponse>
    suspend fun getCharacterStories(characterId:Int): Resource<StoriesResponse>
    suspend fun searchCharacterNameToStartWith(query:String,offset: Int?): Resource<MarvelApiResponse>
    suspend fun saveFavoriteCharacter(character:MarvelCharacter)
    suspend fun saveAllCharacters(characters:List<MarvelCharacter>)
    fun getAllSavedCharacters(): Flow<List<MarvelCharacter>>
    suspend fun deleteSavedCharacter(character: MarvelCharacter)

}