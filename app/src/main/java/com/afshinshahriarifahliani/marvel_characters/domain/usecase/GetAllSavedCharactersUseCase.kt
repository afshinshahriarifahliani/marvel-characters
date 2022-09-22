package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class GetAllSavedCharactersUseCase(private val repository:MarvelRepository) {
    fun execute(): Flow<List<MarvelCharacter>> {
        return repository.getAllSavedCharacters()
    }
}