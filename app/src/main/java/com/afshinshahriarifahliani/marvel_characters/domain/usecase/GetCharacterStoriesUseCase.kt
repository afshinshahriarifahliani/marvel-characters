package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.stories.StoriesResponse
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.util.Resource

class GetCharacterStoriesUseCase(private val repository: MarvelRepository) {
    suspend fun execute(characterId: Int): Resource<StoriesResponse> {
        return repository.getCharacterStories(characterId)
    }
}