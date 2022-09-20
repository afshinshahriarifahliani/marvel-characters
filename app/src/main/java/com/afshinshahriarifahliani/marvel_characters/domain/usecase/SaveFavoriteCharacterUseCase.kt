package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository

class SaveFavoriteCharacterUseCase(private val repository: MarvelRepository) {
    suspend fun execute(){
        repository.saveFavoriteCharacter()
    }
}