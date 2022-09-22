package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.util.Resource

class GetSingleCharacterByIdUseCase(private val repository:MarvelRepository) {

    suspend fun execute(characterId:Int): Resource<MarvelApiResponse> {
        return repository.getSingleCharacterById(characterId)
    }
}