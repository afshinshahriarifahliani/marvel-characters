package com.afshinshahriarifahliani.marvel_characters.presentation.di

import android.app.Application
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.*
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideMarvelViewModelFactory(
        app: Application,
        getAllCharactersUseCase: GetAllCharactersUseCase,
        getSingleCharacterByIdUseCase: GetSingleCharacterByIdUseCase,
        getCharacterComicsUseCase: GetCharacterComicsUseCase,
        getCharacterSeriesUseCase: GetCharacterSeriesUseCase,
        getCharacterEventsUseCase: GetCharacterEventsUseCase,
        getCharacterStoriesUseCase: GetCharacterStoriesUseCase,
        searchCharacterNameToStartWithUseCase:SearchCharacterNameToStartWithUseCase,
        saveFavoriteCharacterUseCase: SaveFavoriteCharacterUseCase,
        getAllSavedCharactersUseCase: GetAllSavedCharactersUseCase,
        deleteSavedCharacterUseCase: DeleteSavedCharacterUseCase
    ): MarvelViewModelFactory {
        return MarvelViewModelFactory(
            app,
            getAllCharactersUseCase,
            getSingleCharacterByIdUseCase,
            getCharacterComicsUseCase,
            getCharacterSeriesUseCase,
            getCharacterEventsUseCase,
            getCharacterStoriesUseCase,
            searchCharacterNameToStartWithUseCase,
            saveFavoriteCharacterUseCase,
            getAllSavedCharactersUseCase,
            deleteSavedCharacterUseCase
        )
    }
}