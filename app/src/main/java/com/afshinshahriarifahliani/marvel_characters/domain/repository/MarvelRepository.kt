package com.afshinshahriarifahliani.marvel_characters.domain.repository

import com.afshinshahriarifahliani.marvel_characters.data.model.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.util.Resource

interface MarvelRepository {
    suspend fun getAllCharacters(offset: Int?): Resource<MarvelApiResponse>
    suspend fun getSingleCharacterById(characterId:Int):Resource<MarvelApiResponse>
    suspend fun saveFavoriteCharacter()
}