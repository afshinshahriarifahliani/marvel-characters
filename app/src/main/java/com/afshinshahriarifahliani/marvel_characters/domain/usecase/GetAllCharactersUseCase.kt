package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository

class GetAllCharactersUseCase(private val repository: MarvelRepository) {
    suspend fun execute(offset: Int?):Resource<MarvelApiResponse> {
        return repository.getAllCharacters(offset)
    }
}