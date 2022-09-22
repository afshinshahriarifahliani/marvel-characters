package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository

class DeleteSavedCharacterUseCase(private val repository: MarvelRepository) {
    suspend fun execute(character: MarvelCharacter){
        repository.deleteSavedCharacter(character)
    }
}