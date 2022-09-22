package com.afshinshahriarifahliani.marvel_characters.domain.usecase

import com.afshinshahriarifahliani.marvel_characters.data.model.series.SeriesResponse
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.util.Resource

class GetCharacterSeriesUseCase(private val repository: MarvelRepository) {
    suspend fun execute(characterId:Int): Resource<SeriesResponse> {
        return repository.getCharacterSeries(characterId)
    }
}