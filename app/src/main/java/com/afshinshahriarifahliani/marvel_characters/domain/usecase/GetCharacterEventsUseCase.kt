package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.events.EventsResponse
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.util.Resource

class GetCharacterEventsUseCase(private val repository: MarvelRepository) {
    suspend fun execute(characterId:Int): Resource<EventsResponse> {
        return repository.getCharacterEvents(characterId)
    }
}