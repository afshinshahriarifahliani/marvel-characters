package com.afshinshahriarifahliani.marvel_characters.presentation.di

import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllCharactersUseCase(repository: MarvelRepository): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSingleCharacterByIdUseCase(repository: MarvelRepository): GetSingleCharacterByIdUseCase {
        return GetSingleCharacterByIdUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCharacterComicsUseCase(repository: MarvelRepository): GetCharacterComicsUseCase {
        return GetCharacterComicsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCharacterSeriesUseCase(repository: MarvelRepository): GetCharacterSeriesUseCase {
        return GetCharacterSeriesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCharacterEventsUseCase(repository: MarvelRepository): GetCharacterEventsUseCase {
        return GetCharacterEventsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCharacterStoriesUseCase(repository: MarvelRepository): GetCharacterStoriesUseCase {
        return GetCharacterStoriesUseCase(repository)
    }
}