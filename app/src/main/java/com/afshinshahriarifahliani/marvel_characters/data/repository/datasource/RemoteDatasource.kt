package com.afshinshahriarifahliani.marvel_characters.data.repository.datasource

import com.afshinshahriarifahliani.marvel_characters.data.model.MarvelApiResponse
import retrofit2.Response

interface RemoteDatasource {
    suspend fun getAllCharacters(offset: Int?):Response<MarvelApiResponse>
    suspend fun getSingleCharacterById(characterId:Int):Response<MarvelApiResponse>
}