package com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.*

class MarvelViewModelFactory(
    private val app: Application,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getSingleCharacterByIdUseCase: GetSingleCharacterByIdUseCase,
    private val getCharacterComicsUseCase: GetCharacterComicsUseCase,
    private val getCharacterSeriesUseCase: GetCharacterSeriesUseCase,
    private val getCharacterEventsUseCase: GetCharacterEventsUseCase,
    private val getCharacterStoriesUseCase: GetCharacterStoriesUseCase,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarvelViewModel::class.java)) {
            return MarvelViewModel(
                app,
                getAllCharactersUseCase,
                getSingleCharacterByIdUseCase,
                getCharacterComicsUseCase,
                getCharacterSeriesUseCase,
                getCharacterEventsUseCase,
                getCharacterStoriesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}