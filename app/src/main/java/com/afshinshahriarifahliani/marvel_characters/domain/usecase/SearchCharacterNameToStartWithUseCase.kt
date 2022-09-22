package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.util.Resource

class SearchCharacterNameToStartWithUseCase(private val repository: MarvelRepository) {
    suspend fun execute(query:String, offset:Int?): Resource<MarvelApiResponse> {
        return repository.searchCharacterNameToStartWith(query,offset)
    }
}