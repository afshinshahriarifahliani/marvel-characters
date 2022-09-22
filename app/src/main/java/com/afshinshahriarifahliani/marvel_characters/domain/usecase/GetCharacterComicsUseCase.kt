package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.comics.ComicResponse
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.util.Resource

class GetCharacterComicsUseCase(private val repository:MarvelRepository) {
    suspend fun execute(characterId:Int): Resource<ComicResponse> {
        return repository.getCharacterComics(characterId)
    }
}