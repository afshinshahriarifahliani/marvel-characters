package com.afshinshahriarifahliani.marvel_characters.data.repository.datasource

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.comics.ComicResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.events.EventsResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.series.SeriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.stories.StoriesResponse
import retrofit2.Response

interface RemoteDatasource {
    suspend fun getAllCharacters(offset: Int?):Response<MarvelApiResponse>
    suspend fun getSingleCharacterById(characterId:Int):Response<MarvelApiResponse>
    suspend fun getCharacterComics(characterId:Int):Response<ComicResponse>
    suspend fun getCharacterSeries(characterId:Int):Response<SeriesResponse>
    suspend fun getCharacterEvents(characterId:Int):Response<EventsResponse>
    suspend fun getCharacterStories(characterId:Int):Response<StoriesResponse>
    suspend fun searchCharacterNameToStartWith(query:String,offset: Int?):Response<MarvelApiResponse>
}