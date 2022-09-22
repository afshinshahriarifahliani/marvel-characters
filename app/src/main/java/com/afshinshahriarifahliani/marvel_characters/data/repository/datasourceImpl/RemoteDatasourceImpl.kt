package com.afshinshahriarifahliani.marvel_characters.data.repository.datasourceImpl

import com.afshinshahriarifahliani.marvel_characters.data.api.MarvelApiService
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.comics.ComicResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.events.EventsResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.series.SeriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.stories.StoriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.RemoteDatasource
import retrofit2.Response

class RemoteDatasourceImpl(private val marvelApiService: MarvelApiService):RemoteDatasource {
    override suspend fun getAllCharacters(offset: Int?): Response<MarvelApiResponse> {
        return marvelApiService.getCharacters(offset)
    }

    override suspend fun getSingleCharacterById(characterId: Int): Response<MarvelApiResponse> {
        return marvelApiService.getSingleCharacterById(characterId)
    }

    override suspend fun getCharacterComics(characterId: Int): Response<ComicResponse> {
        return marvelApiService.getCharacterComics(characterId)
    }

    override suspend fun getCharacterSeries(characterId: Int): Response<SeriesResponse> {
        return marvelApiService.getCharacterSeries(characterId)
    }

    override suspend fun getCharacterEvents(characterId: Int): Response<EventsResponse> {
        return marvelApiService.getCharacterEvents(characterId)
    }

    override suspend fun getCharacterStories(characterId: Int): Response<StoriesResponse> {
       return marvelApiService.getCharacterStories(characterId)
    }
}