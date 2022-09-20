package com.afshinshahriarifahliani.marvel_characters.data.repository.datasourceImpl

import com.afshinshahriarifahliani.marvel_characters.data.api.MarvelApiService
import com.afshinshahriarifahliani.marvel_characters.data.model.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.RemoteDatasource
import retrofit2.Response

class RemoteDatasourceImpl(private val marvelApiService: MarvelApiService):RemoteDatasource {
    override suspend fun getAllCharacters(offset: Int?): Response<MarvelApiResponse> {
        return marvelApiService.getCharacters(offset)
    }

    override suspend fun getSingleCharacterById(characterId: Int): Response<MarvelApiResponse> {
        return marvelApiService.getSingleCharacterById(characterId)
    }
}